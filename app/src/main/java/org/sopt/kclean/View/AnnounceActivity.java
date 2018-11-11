package org.sopt.kclean.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import org.sopt.kclean.Controller.AdapterAnnounceList;
import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        // xml
        notice_recycler_view = (RecyclerView) findViewById(R.id.notice_recycler_view);
        announce_write_imgBtn = (ImageButton) findViewById(R.id.announce_write_imgBtn);

        // 작성하기 버튼 리스너
        announce_write_imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnounceActivity.this, WriteAnnounceActivity.class); // 공지 작성 화면 띄우기
                startActivity(intent);
            }
        });

        // 공지 리스트 데이터
        announceList = new ArrayList<Notice>();
        announceList.add(new Notice());

        // RecyclerView 설정
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        notice_recycler_view.setLayoutManager(layoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
        announceListAdapter = new AdapterAnnounceList(getApplicationContext(), announceList);
        notice_recycler_view.setAdapter(announceListAdapter); // RecyclerView 어댑터 설정
    }
}