package org.sopt.kclean.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterAnnounceList;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.AdapterSearchGroupList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.R;

import java.io.IOException;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    //
    private ArrayList<Group> searchGroupList;

    private EditText search_search_text;
    private Button search_ok_button;
    private RecyclerView search_result_recycler;

    // RecyclerView 관련
    private LinearLayoutManager layoutManager;
    private AdapterSearchGroupList searchGroupListAdapter;

    //
    private String word = "";
    private String response = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_search_text = (EditText) findViewById(R.id.search_search_text);
        search_ok_button = (Button) findViewById(R.id.search_ok_button);
        search_result_recycler = (RecyclerView) findViewById(R.id.search_result_recycler);


        // 확인 버튼 -> 검색 통신
        search_ok_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 통신
                Toast.makeText(getApplicationContext(), "버튼 눌러써 ㅋ", Toast.LENGTH_SHORT).show();
                word = search_search_text.getText().toString();


                SearchTask task = new SearchTask();
                task.execute();
            }
        });
    }

    private class SearchTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            // request 보내깅
            Get get = new Get("word", word); // Get 객체 생성

            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/club/search","application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            // response 받아온 거 ,,,
            JSONArray jsonArray = null;
            try {
                JSONObject jsonObject= new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("data");

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

            super.onPostExecute(s);
            // 그룹 리스트 객체 생성
            searchGroupList = new ArrayList<Group>();
            if(jsonArray == null)
                return;

            // 받아온 response에서 searchGroupList에 group 추가
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Group group = new Group(jsonObject.getString("club_id"), jsonObject.getString("club_name"), jsonObject.getString("club_explanation"), jsonObject.getString("club_logo"), jsonObject.getString("club_manager"), jsonObject.getInt("club_count"));
                    searchGroupList.add(group);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // RecyclerView 관련 설정
            layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            search_result_recycler.setLayoutManager(layoutManager);
            searchGroupListAdapter = new AdapterSearchGroupList(getApplicationContext(), searchGroupList);
            search_result_recycler.setAdapter(searchGroupListAdapter);
        }
    }
}
