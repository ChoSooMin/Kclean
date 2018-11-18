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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupDetailActivity extends AppCompatActivity {

    private ImageButton group_detail_announce_button; // 공지 버튼
    private ImageButton group_detail_finance_button; // 재정 버튼
    private ImageButton group_detail_board_button; // 게시판 버튼
    private ImageButton group_detail_member_button; // 회원 버튼

    // 동아리 정보 설정
    private TextView group_detail_groupName_text; // 동아리 이름
    private ImageView group_detail_groupBackground_image; // 동아리 배경사진
    private CircleImageView group_detail_group_circleImage; // 동아리 로고
    private TextView group_detail_groupDetail_text; // 동아리 설명
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

    private Button group_detail_join_button; // 가입하기 버튼

    private Group group; // 현재 그룹 정보를 담은 group 객체
    private String[] dayString = {"일", "월", "화", "수", "목", "금", "토"};
    private User user; // 앞에서 user 객체 받아오기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user"); // user 객체 받아옴
        group = getIntent().getParcelableExtra("selectedGroup"); // 앞에서 intent에 담아 보낸 Group 객체 가져오기

        // xml 연결
        group_detail_announce_button = (ImageButton) findViewById(R.id.group_detail_announce_button); // 공지 버튼
        group_detail_finance_button = (ImageButton) findViewById(R.id.group_detail_finance_button); // 재정 버튼
        group_detail_board_button = (ImageButton) findViewById(R.id.group_detail_board_button); // 게시판 버튼
        group_detail_member_button = (ImageButton) findViewById(R.id.group_detail_member_button); // 회원 버튼
        group_detail_groupName_text = (TextView) findViewById(R.id.group_detail_groupName_text); // 동아리 이름
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

        group_detail_join_button = (Button) findViewById(R.id.group_detail_join_button);

        new GroupDetailTask().execute();

        // 버튼 리스너
        // 공지 버튼 리스너
        group_detail_announce_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, AnnounceActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("group", group);

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
                intent.putExtra("user", user);

                startActivity(intent);
            }
        });

        // 회원 버튼 리스너
        group_detail_member_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupDetailActivity.this, MemberActivity.class);
                intent.putExtra("user", user);

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

                Log.v("groupDetail", "요긴 성공");
                groupObject = jsonObject.getJSONObject("data");
                clubInfo = groupObject.getJSONObject("clubInfo");
                noticeschedule = groupObject.getJSONObject("noticeschedule");
                totalNotice = groupObject.getJSONObject("totalNotice");
                user_data = groupObject.getJSONObject("user_data");
            } catch (JSONException e) {
                Log.v("groupDetail", "!! Exception !!"); //  이쓍,,, 왜,,, Exception,,

                e.printStackTrace();
                return;
            }

            if (groupObject == null || clubInfo == null || noticeschedule == null || totalNotice == null || user_data == null) {
                Log.v("groupDetail", "returnㅜㅜ");

                return;
            }

            try {
                Log.v("groupDetail", "들어와띠");

                group_detail_groupName_text.setText(clubInfo.getString("club_name")); // 그룹 이름 설정
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_background")).asBitmap().centerCrop().into(group_detail_groupBackground_image); // 그룹 배경사진 설정
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_logo")).asBitmap().centerCrop().into(group_detail_group_circleImage); // 그룹 대표사진 설정
                group_detail_groupDetail_text.setText(clubInfo.getString("club_explanation")); // 그룹 설명 설정


                // 다가오는 일정 설정
                group_detail_comingTitle_text.setText(noticeschedule.getString("notice_title"));
                // 날짜
                String dateString = noticeschedule.getString("notice_date"); // 다가오는 일정 날짜 (2018-10-02 이런식,,,)
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
                Date formatDate = transFormat.parse(dateString);

                group_detail_comingMonth_text.setText((formatDate.getMonth() + 1) +  ""); // 다가오는 일정 (월)
                group_detail_comingDay_text.setText(formatDate.getDate() + ""); // 다가오는 일정 (일)
                // 여기까지 날짜
                group_detail_comingTime_text.setText(noticeschedule.getString("notice_time")); // 다가오는 일정 시간 설정

                // 최신 공지 설정
                // 최신 공지 타입 이미지 설정
                int announceType = totalNotice.getInt("notice_category");
                if (announceType == 0) {
                    group_detail_type_image.setImageResource(R.drawable.ic_normal_notice);
                }
                else if (announceType == 1) {
                    group_detail_type_image.setImageResource(R.drawable.ic_special_notice);
                }
                // 공지 작성 날짜 설정
                String announceDateString = totalNotice.getString("write_time"); // 공지 작성한 날짜
                Date announceDate = transFormat.parse(announceDateString);
                int announceMonth = announceDate.getMonth() + 1;
                int announceDateInt = announceDate.getDate();
                int announceHour = announceDate.getHours();
                int announceMin = announceDate.getMinutes();

                String finalDate = announceMonth + "/" + announceDateInt;
                String finalTime = announceHour + ":" + announceMin;

                if (announceMin == 0) {
                    finalTime += "0";
                }

                group_detail_announceDate_text.setText(finalDate); // 공지 작성 날짜 설정
                group_detail_announceTime_text.setText(finalTime); // 공지 작성 시간 설정
                //
                group_detail_announceTitle_text.setText(totalNotice.getString("notice_title")); // 공지 제목 설정
                group_detail_announceContent_text.setText(totalNotice.getString("notice_content")); // 공지 내용 설정

                int notice_id = totalNotice.getInt("notice_id"); // 공지 id 가져오기

                // user_data 처리
//                int club_checking = user_data.getInt("club_checking"); // 동아리에 가입이 되어있는지 확인 || 0이면 가입 안됨, 1이면 가입 됨 || 0이면 버튼 나타나고 1이면 버튼 안나타남
                int club_checking = 1;
                if (club_checking == 0) {
                    group_detail_join_button.setVisibility(View.VISIBLE);
                }
                else if (club_checking == 1) {
                    group_detail_join_button.setVisibility(View.GONE);
                }

                int user_position;
                try {
                    user_position = user_data.getInt("user_position"); // 0이면 총무, 1이면 일반 회원
                } catch(NullPointerException e) {
                    user_position = -1; // -1이면 가입 안한거
                }


            } catch(JSONException e) {
                e.printStackTrace();

                return;
            } catch (ParseException e) {
                e.printStackTrace();

                return;
            }

        }
    }
}
