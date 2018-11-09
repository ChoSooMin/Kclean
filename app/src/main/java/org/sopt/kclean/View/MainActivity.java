package org.sopt.kclean.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button main_createGroupBtn;
    private Button main_search_button;


    // 탭
    private TabHost tabHost;
    private TabWidget tabWidget;
    private FrameLayout frameLayout;

    //
    private RecyclerView recyclerView;
    private AdapterGroupList groupListAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 탭탭탭~~
        tabHost = (TabHost)findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1").setContent(R.id.content1).setIndicator("홈");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2").setContent(R.id.content2).setIndicator("알림");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("3").setContent(R.id.content3).setIndicator("더보기");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);


        groups = new ArrayList<Group>();

//        groups.add(new Group("SOPT_23",484,"류지훈", "", getDrawable(R.drawable.sopt), "최효진 최고"));

        recyclerView =  (RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        groupListAdapter = new AdapterGroupList(getApplicationContext(), groups);
        recyclerView.setAdapter(groupListAdapter);
        // 요까지 탭탭탭~~


        // 동아리 생성 버튼
        main_createGroupBtn = (Button) findViewById(R.id.main_createGroupBtn);
        main_createGroupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 동아리 생성 화면으로
                Intent intent=new Intent(MainActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });

        // 검색 버튼
        main_search_button = (Button) findViewById(R.id.main_search_button);
        main_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    // tab 눌러질 때 처리
    void init(){
        frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String number) {
                switch (number) {

                    case "1":
                        frameLayout.setBackgroundColor(Color.parseColor("#eaeaea"));
                        break;




                    case "2":
                        frameLayout.setBackgroundColor(Color.parseColor("#eaeaea"));
                        break;
                }
            }
        });
    }

}
