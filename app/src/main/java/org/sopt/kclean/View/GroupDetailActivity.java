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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private LinearLayout group_detail_notice_linear;

    private Group group; // 현재 그룹 정보를 담은 group 객체
    private String[] dayString = {"일", "월", "화", "수", "목", "금", "토"};
    private User user; // 앞에서 user 객체 받아오기

    int user_position = -1; // 일반회원? 총무? 가입X?


    //통신해서 받아온 파라미터
    private int club_checking;
    private String notice_id;

    //
    private String totalNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user"); // user 객체 받아옴
        group = getIntent().getParcelableExtra("selectedGroup"); // 앞에서 intent에 담아 보낸 Group 객체 가져오기
        totalNumber = getIntent().getStringExtra("totalNumber");

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
        group_detail_notice_linear = (LinearLayout)findViewById(R.id.group_detail_notice_linear);//공지 레이아웃
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

        //가입버튼
        group_detail_join_button = (Button) findViewById(R.id.group_detail_join_button);

        new GroupDetailTask().execute(); // 통신

//        Log.v("groupDetaildd", "user_positiokn ||" + user_position); // 우엥
//
//
//        // 내가 옮겨봤다ㅡ,, 이거 삭제하구 아래에 똑같은 코드 주석처리 된 거 풀면 된댱
//        if (club_checking == 0) { //
//            group_detail_join_button.setVisibility(View.VISIBLE);
//            group_detail_join_button.setOnClickListener(new View.OnClickListener() {
//
//                //가입버튼
//                @Override
//                public void onClick(View v) {
//                    //가입 통신 필요
//                    new GroupJoinTask().execute();
//                    //다이얼로그하고
//                    finish();
//                }
//            });
//        }
//        else if (club_checking == 1) {
//            group_detail_join_button.setVisibility(View.GONE);
//        }
//        //
//
//        // 요놈도 내가 옮겨봤다ㅡ,, 이거 삭제하구 아래에 똑같은 코드 주석처리 된 거 풀면 된댱
//        //최신공지로 이동
//        group_detail_notice_linear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupDetailActivity.this,AnnounceDetailActivity.class);
//                intent.putExtra("notice_id",notice_id);
//                startActivity(intent);
//            }
//        });
//
//
//
//        // 공지버튼
//        group_detail_announce_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupDetailActivity.this, AnnounceActivity.class);
//                intent.putExtra("user", user);
//                intent.putExtra("group", group);
//                intent.putExtra("user_position", user_position);
//
//                startActivity(intent);
//            }
//        });
//
//        // 재정 버튼 리스너
//        group_detail_finance_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupDetailActivity.this, FinancialDetailActivity.class);
//                intent.putExtra("user", user);
//                intent.putExtra("selectedGroup", group);
//                startActivity(intent);
//            }
//        });
//
//        // 게시판 버튼 리스너
//        group_detail_board_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupDetailActivity.this, BoardActivity.class);
//                intent.putExtra("user", user);
//
//                startActivity(intent);
//            }
//        });
//
//        // 회원 버튼 리스너
//        group_detail_member_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(GroupDetailActivity.this, MemberActivity.class);
//                intent.putExtra("user", user);
//
//                startActivity(intent);
//            }
//        });
//
//        Log.v("groupCheckingaa", club_checking + "");
//        if (club_checking == 0) {
//            group_detail_join_button.setVisibility(View.GONE);
//        }
//        else if(club_checking == 1) {
//            group_detail_join_button.setVisibility(View.VISIBLE );
//        }

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

                user_position = user_data.getInt("user_position"); // 0이면 총무, 1이면 일반 회원, 2면 가입 안함
                Log.v("groupDetailUser (가져온 직후)", "user_position || " + user_position);

                Log.v("clubclub", clubInfo.getString("club_id"));

                group_detail_groupName_text.setText(clubInfo.getString("club_name")); // 그룹 이름 설정
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_background")).asBitmap().centerCrop().into(group_detail_groupBackground_image); // 그룹 배경사진 설정
                Glide.with(getApplicationContext()).load(clubInfo.getString("club_logo")).asBitmap().centerCrop().into(group_detail_group_circleImage); // 그룹 대표사진 설정
                group_detail_groupDetail_text.setText(clubInfo.getString("club_explanation")); // 그룹 설명 설정


                // 다가오는 일정 설정
                group_detail_comingTitle_text.setText(noticeschedule.getString("notice_title"));
                // 날짜
                String dateString = noticeschedule.getString("notice_date"); // 다가오는 일정 날짜 (2018-10-02 이런식,,,)
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
                if (dateString != null) {
                    Date formatDate = transFormat.parse(dateString);

                    Calendar comingCalendar = new GregorianCalendar();
                    comingCalendar.setTime(formatDate);

                    group_detail_comingMonth_text.setText((comingCalendar.get(Calendar.MONTH) + 1) +  ""); // 다가오는 일정 (월)
                    group_detail_comingDay_text.setText(comingCalendar.get(Calendar.DATE) + ""); // 다가오는 일정 (일)
                }


                // 여기까지 날짜
                group_detail_comingTime_text.setText(noticeschedule.getString("notice_time")); // 다가오는 일정 시간 설정

                // 최신 공지 설정
                // 최신 공지 타입 이미지 설정
                int announceType;
                try {
                    announceType = totalNotice.getInt("notice_category");
                }
                catch (JSONException e)
                {
                    announceType = 2;

                }
                if (announceType == 0) {
                    group_detail_type_image.setImageResource(R.drawable.ic_normal_notice);
                }
                else if (announceType == 1) {
                    group_detail_type_image.setImageResource(R.drawable.ic_special_notice);
                }

                // 공지 작성 날짜 설정
                String announceDateString = totalNotice.getString("write_time"); // 공지 작성한 날짜
                Date announceDate = transFormat.parse(announceDateString);

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(announceDate);

                int announceMonth = calendar.get(Calendar.MONTH) + 1;
                int announceDateInt = calendar.get(Calendar.DATE);
                int announceHour = calendar.get(Calendar.HOUR_OF_DAY);
                int announceMin = calendar.get(Calendar.MINUTE);

                String finalDate = announceMonth + "/" + announceDateInt;
                String finalTime = announceHour + ":" + announceMin;

                if (announceMin == 0) {
                    finalTime += "0";
                }

                group_detail_announceDate_text.setText(finalDate); // 공지 작성 날짜 설정
                group_detail_announceTime_text.setText(finalTime); // 공지 작성 시간 설정
                group_detail_announceTitle_text.setText(totalNotice.getString("notice_title")); // 공지 제목 설정
                group_detail_announceContent_text.setText(totalNotice.getString("notice_content")); // 공지 내용 설정

                notice_id = totalNotice.getString("notice_id"); // 공지 id 가져오기

                // user_data 처리
                club_checking = user_data.getInt("club_checking"); // 동아리에 가입이 되어있는지 확인 || 0이면 가입 안됨, 1이면 가입 됨 || 0이면 버튼 나타나고 1이면 버튼 안나타남
                Log.v("groupChecking", club_checking + "");
                if (club_checking == 0) { //
                    group_detail_join_button.setVisibility(View.VISIBLE);
                    group_detail_join_button.setOnClickListener(new View.OnClickListener() {

                        //가입버튼
                        @Override
                        public void onClick(View v) {
                            //가입 통신 필요
                            new GroupJoinTask().execute();
                            //다이얼로그하고
                            finish();
                        }
                    });
                }
                else if (club_checking == 1) {
                    group_detail_join_button.setVisibility(View.GONE);
                }



            } catch(JSONException e) {
                Log.v("groupDetail", "JSONException!!");
                e.printStackTrace();

                return;
            }
            catch (ParseException e) {
                Log.v("groupDetail", "ParseException!!");
                e.printStackTrace();

                return;
            }

            // 버튼 리스너
            // 공지 버튼 리스너
            if(club_checking == 1) {

                //최신공지로 이동
               group_detail_notice_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GroupDetailActivity.this,AnnounceDetailActivity.class);
                        intent.putExtra("notice_id",notice_id);
                        startActivity(intent);
                    }
                });

                // 공지 버튼 리스너
                group_detail_announce_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Log.v("groupgroup", "공지공지");
                        Intent intent = new Intent(GroupDetailActivity.this, AnnounceActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("group", group);
                        intent.putExtra("user_position", user_position);

                        startActivity(intent);
                    }
                });

                // 재정 버튼 리스너
                group_detail_finance_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GroupDetailActivity.this, FinancialDetailActivity.class);
                        Log.v("groupgroup", "재정재정");
                        intent.putExtra("user", user);
                        intent.putExtra("selectedGroup", group);
                        startActivity(intent);
                    }
                });

                // 게시판 버튼 리스너
                group_detail_board_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GroupDetailActivity.this, BoardActivity.class);
                        Log.v("groupgroup", "게시게시");
                        intent.putExtra("user", user);

                        startActivity(intent);
                    }
                });

                // 회원 버튼 리스너
                group_detail_member_button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GroupDetailActivity.this, MemberActivity.class);
                        Log.v("groupgroup", "회원회원");
                        intent.putExtra("user", user);
                        intent.putExtra("totalNumber", totalNumber);

                        startActivity(intent);
                    }
                });

            }
        }
    }

    // 통신 (그룹 가입)
    private class GroupJoinTask extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            Post post = new Post("https://klean.apps.dev.clayon.io/api/club/join", PostString.groupJoinJson(group.getGroupId()),user.getToken(),"application/x-www-form-urlencoded");

            String response = null;

            try {
                response = post.post();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject jsonObject = null;

            if(s != null) { //응답 성공시
                try {
                    jsonObject = new JSONObject(s);
                    String message = jsonObject.getString("message");

                    if (message.equals("Success to enter club")) {
                        Toast.makeText(getApplicationContext(), "동아리 가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(GroupDetailActivity.this, MainActivity.class);
//
//                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                Toast.makeText(getApplicationContext(), "동아리 가입 실패", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }



}
