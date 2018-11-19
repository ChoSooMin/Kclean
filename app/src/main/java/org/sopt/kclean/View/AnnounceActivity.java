package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterAnnounceList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;
import java.util.ArrayList;

public class AnnounceActivity extends AppCompatActivity {

    // 공지 리스트
    private ArrayList<Notice> announceList;

    // xml
    private RecyclerView notice_recycler_view;
    private ImageButton announce_write_imgBtn;

    // RecyclerView 관련
    private LinearLayoutManager layoutManager;
    private AdapterAnnounceList announceListAdapter;

    User user; // user 객체 받아오기
    Group group; // group 객체 받아오기

    private String response = "";

    int user_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        user = getIntent().getParcelableExtra("user");
        group = getIntent().getParcelableExtra("group");
        user_position = getIntent().getIntExtra("user_position", -1);

        // xml
        notice_recycler_view = (RecyclerView) findViewById(R.id.notice_recycler_view);
        announce_write_imgBtn = (ImageButton) findViewById(R.id.announce_write_imgBtn);

        Log.v("announceActivity(get직후)", "user_position || " + user_position);
        // 총무면 작성하기 버튼 나타나고, 총무가 아니면 작성하기 버튼이 나타나지 않도록
        if (user_position == 0) {
            announce_write_imgBtn.setVisibility(View.VISIBLE);
        }
        else if (user_position == 1) {
            announce_write_imgBtn.setVisibility(View.GONE);
        }

        // 작성하기 버튼 리스너
        announce_write_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnounceActivity.this, WriteAnnounceActivity.class); // 공지 작성 화면 띄우기
                intent.putExtra("user", user);
                intent.putExtra("group", group);

                startActivity(intent);
            }
        });

        // 공지 리스트 데이터
        announceList = new ArrayList<Notice>(); // 공지 리스트 객체 만들기
        //

        // RecyclerView 설정
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        notice_recycler_view.setLayoutManager(layoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
        announceListAdapter = new AdapterAnnounceList(getApplicationContext(), announceList, user, user_position);
        notice_recycler_view.setAdapter(announceListAdapter); // RecyclerView 어댑터 설정

        new AnnounceTask().execute();
    }

    private class AnnounceTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get(user.getToken(), "club_id", group.getGroupId());

            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/club/notice", "application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONArray jsonArray = null;

            if (s == null) {
                return;
            }

            try {
                JSONObject jsonObject= new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("data");

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

            super.onPostExecute(s);

            if(jsonArray == null)
                return;

            // 받아온 response에서 searchGroupList에 group 추가
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Notice notice = new Notice(jsonObject.getString("_id"), jsonObject.getString("write_time"), jsonObject.getString("notice_title"), jsonObject.getInt("notice_category"), jsonObject.getString("notice_content"));
                    announceList.add(notice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // RecyclerView 관련 설정
            layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            notice_recycler_view.setLayoutManager(layoutManager);

            Log.v("announceActivity(통신 후)", "user_position || " + user_position);
            announceListAdapter = new AdapterAnnounceList(getApplicationContext(), announceList, user, user_position);
            notice_recycler_view.setAdapter(announceListAdapter);
        }
    }
}