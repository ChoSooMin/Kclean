package org.sopt.kclean.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.AdapterMainNoticeList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton main_createGroupBtn;
    private ImageButton main_search_button;


    // 탭
    private TabHost tabHost;
    private TabWidget tabWidget;
    private FrameLayout frameLayout;
    private User user = new User();
    private TabHost.TabSpec tab1, tab2, tab3;

    //
    private RecyclerView recyclerView;
    private AdapterGroupList groupListAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Group> groups;

    // 알림
    private RecyclerView main_notice_recycler_view; // 알림 RecyclerView
    private AdapterMainNoticeList mainNoticeListAdapter;
    private LinearLayoutManager noticeLayoutManager;
    private ArrayList<Notice> noticeList;

    //
    private ImageButton mypage_setting;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

        new MainTask().execute();
        new AlarmTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        user = new User();
        user.setToken(intent.getStringExtra("token"));

        // 자동로그인할 때 유저 객체 잘 가져오냐,?
//        Log.v("loginlogin", user.getToken()); // 잘 가져오는군요,,,

        // 탭탭탭~~
        tabHost = (TabHost)findViewById(R.id.tabHost);

        tabHost.setup();

//        ImageView tabWidget01 = new ImageView(this);
//        tabWidget01.setImageResource(R.drawable.tab_01);
//
//        ImageView tabWidget02 = new ImageView(this);
//        tabWidget01.setImageResource(R.drawable.tab_02);
//
//        ImageView tabWidget03 = new ImageView(this);
//        tabWidget01.setImageResource(R.drawable.tab_03);


        tab1 = tabHost.newTabSpec("1").setContent(R.id.content1).setIndicator("", getDrawable(R.drawable.tab_01));
        tab2 = tabHost.newTabSpec("2").setContent(R.id.content2).setIndicator("", getDrawable(R.drawable.tab_02));
        tab3 = tabHost.newTabSpec("3").setContent(R.id.content3).setIndicator("", getDrawable(R.drawable.tab_02));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

//        init();
        // 요까지 탭탭탭~~

        // 마이페이지에서 세팅
        mypage_setting = (ImageButton) findViewById(R.id.mypage_setting);
        mypage_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AccountRegisterActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        MainTask mainTask = new  MainTask();
        mainTask.execute();

        new AlarmTask().execute();

        // 동아리 생성 버튼
        main_createGroupBtn = (ImageButton) findViewById(R.id.main_createGroupBtn);
        main_createGroupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 동아리 생성 화면으로
                Intent intent=new Intent(MainActivity.this, CreateGroupActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        // 검색 버튼
        main_search_button = (ImageButton) findViewById(R.id.main_search_button);
        main_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

//    // tab 눌러질 때 처리
//    void init(){
//        frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);
//
//
//
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//
//
//            @Override
//            public void onTabChanged(String number) {
//                Log.v("tabtab", number);
//                switch (number) {
//                    case "1":
//
//                        break;
//
//                    case "2":
//
//                        break;
//                    case "3" :
//                        break;
//                }
//            }
//        });
//    }

    // 통신(내 동아리 리스트,,,)
    private class MainTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get(user.getToken());

            String response = null;
            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/club","application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.v("ssss", s + "");

            JSONArray jsonArray = null;

            if(s == null)
                return;

            try {
                JSONObject jsonObject= new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("data");

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

            groups = new ArrayList<Group>();
            if(jsonArray == null)
                return;

            for(int i = 0;  i <jsonArray.length() ;i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Group group =  new Group(jsonObject.getString("club_id"),jsonObject.getString("club_name"),jsonObject.getString("club_logo"),jsonObject.getString("club_manager"),jsonObject.getInt("club_count"));
                    groups.add(group);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            recyclerView =  (RecyclerView)findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
            groupListAdapter = new AdapterGroupList(getApplicationContext(), groups, user);
            recyclerView.setAdapter(groupListAdapter);
        }
    }

    // 통신(알림 리스트)
    private class AlarmTask extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get(user.getToken());

            String response = null;
            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/alram","application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.v("ssss", s + "");

            JSONArray jsonArray = null;

            if(s == null)
                return;

            try {
                JSONObject jsonObject= new JSONObject(s);
                jsonArray = jsonObject.getJSONArray("data");

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

            noticeList = new ArrayList<Notice>(); // 알림 리스트 객체 생성
            if(jsonArray == null)
                return;

            for(int i = 0;  i <jsonArray.length() ;i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Notice notice = new Notice(jsonObject.getString("club_logo"), jsonObject.getString("club_name"), jsonObject.getString("club_manager"), jsonObject.getString("write_time"), jsonObject.getString("notice_id"), jsonObject.getString("notice_title"), jsonObject.getString("notice_content"), jsonObject.getInt("account_check"));
                    noticeList.add(notice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            main_notice_recycler_view =  (RecyclerView)findViewById(R.id.main_notice_recycler_view);
            noticeLayoutManager = new LinearLayoutManager(getApplicationContext());
            noticeLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            main_notice_recycler_view.setLayoutManager(noticeLayoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
            mainNoticeListAdapter = new AdapterMainNoticeList(getApplicationContext(), noticeList, user);
            main_notice_recycler_view.setAdapter(mainNoticeListAdapter);
        }
    }
}
