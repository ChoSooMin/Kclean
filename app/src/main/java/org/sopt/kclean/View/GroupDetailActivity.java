package org.sopt.kclean.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.sopt.kclean.R;

import java.lang.reflect.Member;

public class GroupDetailActivity extends AppCompatActivity {

    private ImageButton group_detail_announce_button; // 공지 버튼
    private ImageButton group_detail_finance_button; // 재정 버튼
    private ImageButton group_detail_board_button; // 게시판 버튼
    private ImageButton group_detail_member_button; // 회원 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        // xml 연결
        group_detail_announce_button = (ImageButton) findViewById(R.id.group_detail_announce_button); // 공지 버튼
        group_detail_finance_button = (ImageButton) findViewById(R.id.group_detail_finance_button); // 재정 버튼
        group_detail_board_button = (ImageButton) findViewById(R.id.group_detail_board_button); // 게시판 버튼
        group_detail_member_button = (ImageButton) findViewById(R.id.group_detail_member_button); // 회원 버튼


        // 버튼 리스너
        // 공지 버튼 리스너
        group_detail_announce_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, AnnounceActivity.class);
                startActivity(intent);
            }
        });

        // 재정 버튼 리스너
        group_detail_finance_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, FinancialDetailActivity.class);
                startActivity(intent);
            }
        });

        // 게시판 버튼 리스너
        group_detail_board_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, BoardActivity.class);
                startActivity(intent);
            }
        });

        // 회원 버튼 리스너
        group_detail_member_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, MemberActivity.class);
                startActivity(intent);
            }
        });
    }
}
