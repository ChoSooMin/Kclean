package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


public class GroupDetailActivity extends AppCompatActivity {

    private ImageButton group_detail_announce_button; // 공지 버튼
    private ImageButton group_detail_finance_button; // 재정 버튼
    private ImageButton group_detail_board_button; // 게시판 버튼
    private ImageButton group_detail_member_button; // 회원 버튼
    private ImageView group_detail_groupBackground_image;
    private CircleImageView group_detail_group_circleImage;
    private TextView group_detail_groupDetail_text;
    // 다가오는 일정
    private TextView group_detail_comingDay_text; // 다가오는 일정 (일)
    private TextView group_detail_comingMonth_text; // 다가오는 일정 (월)
    private TextView group_detail_comingTitle_text; // 다가오는 일정 (일정 제목)
    private TextView group_detail_comingTime_text; // 다가오는 일정 (일정 시간)
    // 최신 공지
    private ImageView group_detail_type_image; // 공지 타입
    private TextView group_detail_announceDate_text; // 공지 날짜
    private TextView group_detail_announceTime_text; // 공지 시간
    private TextView group_detail_announceTitle_text; // 공지 제목
    private TextView group_detail_announceContent_text; // 공지 내용
    // 최근 게시물1
    private CircleImageView group_detail_memberImg_circleImg1; // 게시물 회원 사진1
    private TextView group_detail_writer_text1; // 게시물 회원 이름1
    private TextView group_detail_date_text1; // 게시물 날짜1
    private TextView group_detail_time_text1; // 게시물 시간1
    private TextView group_detail_content_text1; // 게시물 내용1
    private TextView group_detail_commentNumber_text1; // 게시물 댓글수1
    private TextView group_detail_likeNumber_text1; // 게시물 좋아요수1
    private TextView group_detail_photoNumber_text1; // 게시물 사진수1
    // 최근 게시물2
    private CircleImageView group_detail_memberImg_circleImg2; // 게시물 회원 사진2
    private TextView group_detail_writer_text2; // 게시물 회원 이름2
    private TextView group_detail_date_text2; // 게시물 날짜2
    private TextView group_detail_time_text2; // 게시물 시간2
    private TextView group_detail_content_text2; // 게시물 내용2
    private TextView group_detail_commentNumber_text2; // 게시물 댓글수2
    private TextView group_detail_likeNumber_text2; // 게시물 좋아요수2
    private TextView group_detail_photoNumber_text2; // 게시물 사진수2

    private Group group; // 현재 그룹 정보를 담은 group 객체
    private User user;
    private String[] dayString = {"월"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");
        group =intent.getParcelableExtra("selectedGroup");

        // xml 연결
        group_detail_announce_button = (ImageButton) findViewById(R.id.group_detail_announce_button); // 공지 버튼
        group_detail_finance_button = (ImageButton) findViewById(R.id.group_detail_finance_button); // 재정 버튼
        group_detail_board_button = (ImageButton) findViewById(R.id.group_detail_board_button); // 게시판 버튼
        group_detail_member_button = (ImageButton) findViewById(R.id.group_detail_member_button); // 회원 버튼
        group_detail_groupBackground_image = (ImageView) findViewById(R.id.group_detail_groupBackground_image); // 그룹 배경사진
        group_detail_group_circleImage = (CircleImageView) findViewById(R.id.group_detail_group_circleImage); // 그룹 대표사진
        group_detail_groupDetail_text = (TextView) findViewById(R.id.group_detail_groupDetail_text); // 그룹 설명
        // 다가오는 일정
        group_detail_comingDay_text = (TextView) findViewById(R.id.group_detail_comingDay_text); // 다가오는 일정 (일)
        group_detail_comingMonth_text = (TextView) findViewById(R.id.group_detail_comingMonth_text); // 다가오는 일정(월)
        group_detail_comingTitle_text = (TextView) findViewById(R.id.group_detail_comingTitle_text); // 다가오는 일정 (일정 제목)
        group_detail_comingTime_text = (TextView) findViewById(R.id.group_detail_comingTime_text); // 다가오는 일정 (일정 시간)
        // 최신 공지
        group_detail_type_image = (ImageView) findViewById(R.id.group_detail_type_image); // 공지 타입 이미지
        group_detail_announceDate_text = (TextView) findViewById(R.id.group_detail_announceDate_text); // 공지 날짜
        group_detail_announceTime_text = (TextView) findViewById(R.id.group_detail_announceTime_text); // 공지 시간
        group_detail_announceTitle_text = (TextView) findViewById(R.id.group_detail_announceTitle_text); // 공지 제목
        group_detail_announceContent_text = (TextView) findViewById(R.id.group_detail_announceContent_text); // 공지 내용
        // 최신 게시물1
        group_detail_memberImg_circleImg1 = (CircleImageView) findViewById(R.id.group_detail_memberImg_circleImg1); // 게시물 회원 사진
        group_detail_writer_text1 = (TextView) findViewById(R.id.group_detail_writer_text1); // 게ㅔ시물 작성자 이름
        group_detail_date_text1 = (TextView) findViewById(R.id.group_detail_date_text1); // 게시 날짜
        group_detail_time_text1 = (TextView) findViewById(R.id.group_detail_time_text1); // 게시 시간
        group_detail_content_text1 = (TextView) findViewById(R.id.group_detail_content_text1); // 게시 내용
        group_detail_commentNumber_text1 = (TextView) findViewById(R.id.group_detail_commentNumber_text1); // 댓글수
        group_detail_likeNumber_text1 = (TextView) findViewById(R.id.group_detail_likeNumber_text1); // 좋아요수
        group_detail_photoNumber_text1 = (TextView) findViewById(R.id.group_detail_photoNumber_text1); // 사진수
        // 최신 게시물2
        group_detail_memberImg_circleImg2 = (CircleImageView) findViewById(R.id.group_detail_memberImg_circleImg2); // 게시물 회원 사진
        group_detail_writer_text2 = (TextView) findViewById(R.id.group_detail_writer_text2); // 게ㅔ시물 작성자 이름
        group_detail_date_text2 = (TextView) findViewById(R.id.group_detail_date_text2); // 게시 날짜
        group_detail_time_text2 = (TextView) findViewById(R.id.group_detail_time_text2); // 게시 시간
        group_detail_content_text2 = (TextView) findViewById(R.id.group_detail_content_text2); // 게시 내용
        group_detail_commentNumber_text2 = (TextView) findViewById(R.id.group_detail_commentNumber_text2); // 댓글수
        group_detail_likeNumber_text2 = (TextView) findViewById(R.id.group_detail_likeNumber_text2); // 좋아요수
        group_detail_photoNumber_text2 = (TextView) findViewById(R.id.group_detail_photoNumber_text2); // 사진수


        new GroupDetailTask().execute();

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
                intent.putExtra("user",user);
                intent.putExtra("selectedGroup",group);
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

    private class GroupDetailTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            //group = (Group) getIntent().getSerializableExtra("selectedGroup"); // 앞에서 intent에 담아 보낸 Group 객체 가져오기
            Get get = new Get(user.getToken(), "club_id", group.getGroupId()); // Get 객체 생성

            String response = null;
            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/club/detail","application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONObject groupObject = null;
            JSONObject clubInfo = null;
            JSONObject noticeschedule = null;
            JSONObject totalNotice = null;
            JSONObject user_data = null;

            try {
                JSONObject jsonObject = new JSONObject(s);

                groupObject = jsonObject.getJSONObject("data");
                clubInfo = jsonObject.getJSONObject("clubInfo");
                noticeschedule = jsonObject.getJSONObject("noticeschedule");
                totalNotice = jsonObject.getJSONObject("totalNotice");
                user_data = jsonObject.getJSONObject("user_data");
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }

            if (groupObject == null || clubInfo == null || noticeschedule == null || totalNotice == null || user_data == null) {
                return;
            }

            try {
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_background")).asBitmap().centerCrop().into(group_detail_groupBackground_image); // 그룹 배경사진 설정
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_logo")).asBitmap().centerCrop().into(group_detail_group_circleImage); // 그룹 대표사진 설정
                group_detail_groupDetail_text.setText(clubInfo.getString("club_explanation")); // 그룹 설명 설정
                // 다가오는 일정 설정
                String dateString = noticeschedule.getString("notice_date"); // 다가오는 일정 날짜 (2018-10-02 이런식,,,)
                Log.v("soominDDDD", dateString);

                int dayInt = noticeschedule.getInt("notice_day"); // 다가오는 일정 요일

//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//                Date date = format.parse(dateString);

            } catch(JSONException e) {
                e.printStackTrace();
                return;
            }

        }
    }
}
