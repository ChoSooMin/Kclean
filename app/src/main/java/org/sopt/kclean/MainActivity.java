package org.sopt.kclean;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends AppCompatActivity {

    Button home_button;
    Button alarm_button;
    Button account_button;
    Button setting_button;
    Button search_button;

    // 탭
    TabHost tabHost;
    TabWidget tabWidget;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost)findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1").setContent(R.id.content1).setIndicator("홈");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2").setContent(R.id.content2).setIndicator("알림");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("3").setContent(R.id.content3).setIndicator("더보기");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);



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
