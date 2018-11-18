package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        user = getIntent().getParcelableExtra("user");
        group = getIntent().getParcelableExtra("group");

        // xml
        notice_recycler_view = (RecyclerView) findViewById(R.id.notice_recycler_view);
        announce_write_imgBtn = (ImageButton) findViewById(R.id.announce_write_imgBtn);

        // 작성하기 버튼 리스너
        announce_write_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnounceActivity.this, WriteAnnounceActivity.class); // 공지 작성 화면 띄우기
                intent.putExtra("user", user);

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
        announceListAdapter = new AdapterAnnounceList(getApplicationContext(), announceList, user);
        notice_recycler_view.setAdapter(announceListAdapter); // RecyclerView 어댑터 설정
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
            super.onPostExecute(s);

            JSONArray jsonArray = null;

            try {
                JSONObject jsonObject = new JSONObject(s);
                String message = jsonObject.getString("message"); // message 가져오기

                if (message.equals("Success to Show list")) {
                    jsonArray = jsonObject.getJSONArray("data");
                }
                else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    jsonArray = null;
                }

                announceList = new ArrayList<Notice>();

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}