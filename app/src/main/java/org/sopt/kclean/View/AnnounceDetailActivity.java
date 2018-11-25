package org.sopt.kclean.View;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterAnnounceList;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.AdapterMemberList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnnounceDetailActivity extends AppCompatActivity {

    // xml
    private TextView announce_detail_announceTitle_text;
    private ImageView announce_detail_announceType_image;
    private CircleImageView announce_detail_memberImage_circle;
    private TextView announce_detail_managerName_text;
    private TextView announce_detail_announceTime_text;
    private TextView announce_detail_date_text;
    private TextView announce_detail_time_text;
    private TextView announce_detail_place_text;
    private TextView announce_detail_announceContent_text;
    private TextView announce_detail_money_text;
    private TextView announce_detail_joinNumber_text;
    private RecyclerView announce_detail_join_recycler;
    private Button announce_detail_manager_button; // 총무 버튼
    private Button announce_detail_member_button; // 일반회원 버튼

    String notice_id = ""; // 공지 id
    String response = "";
    private ArrayList<User> userList;

    private User user;

    private int user_position; // 앞에서 받아온 user_position

    private int managerBtn = 0; // 0이면 "송금요청"
    private int memberBtn = 0; // 0이면 "참석하기" (참석 안한 상태), 1이면 "참석취소" (참석한 상태)

    private Notice notice;


    // RecyclerView
    private AdapterMemberList memberListAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_detail);

        notice_id = getIntent().getStringExtra("notice_id");
        user = getIntent().getParcelableExtra("user");
        user_position = getIntent().getIntExtra("user_position", -1);

        Log.v("announceDetail(가져온 직후)", "user_position" + user_position);
        Log.v("noticenotice", notice_id + "");

        // xml
        announce_detail_announceTitle_text = (TextView) findViewById(R.id.announce_detail_announceTitle_text);
        announce_detail_announceType_image = (ImageView) findViewById(R.id.announce_detail_announceType_image);
        announce_detail_memberImage_circle = (CircleImageView) findViewById(R.id.announce_detail_memberImage_circle);
        announce_detail_managerName_text = (TextView) findViewById(R.id.announce_detail_managerName_text);
        announce_detail_announceTime_text = (TextView) findViewById(R.id.announce_detail_announceTime_text);
        announce_detail_date_text = (TextView) findViewById(R.id.announce_detail_date_text);
        announce_detail_time_text = (TextView) findViewById(R.id.announce_detail_time_text);
        announce_detail_place_text = (TextView) findViewById(R.id.announce_detail_place_text);
        announce_detail_announceContent_text = (TextView) findViewById(R.id.announce_detail_announceContent_text);
        announce_detail_money_text = (TextView) findViewById(R.id.announce_detail_money_text);
        announce_detail_joinNumber_text = (TextView) findViewById(R.id.announce_detail_joinNumber_text);
        announce_detail_join_recycler = (RecyclerView) findViewById(R.id.announce_detail_join_recycler);
        announce_detail_manager_button = (Button) findViewById(R.id.announce_detail_manager_button);
        announce_detail_member_button = (Button) findViewById(R.id.announce_detail_member_button);
        // 여기까지 xml

        new AnnounceDetailTask().execute(notice_id); // 데이터 가져오깅,,,


        if (user_position == 0) { // 0이면 총무
            announce_detail_manager_button.setVisibility(View.VISIBLE);
            announce_detail_member_button.setVisibility(View.GONE);
        }
        else if (user_position == 1) { // 1이면 일반 회원
            announce_detail_member_button.setVisibility(View.VISIBLE);
            announce_detail_manager_button.setVisibility(View.GONE);
        }

        // (총무) 송금 요청 버튼 눌렀을 때
        announce_detail_manager_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new RequestMoneyTask().execute(notice_id);
            }
        });

        // (일반회원) 참석하기 버튼 눌렀을 때
        announce_detail_member_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AttendTask().execute(notice_id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    // 참석하기
    private class AttendTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/club/notice", PostString.attendAnnuonce(strings[0]), user.getToken(),"application/x-www-form-urlencoded");

            String response = null;

            try {
                response =  post.post();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject jsonObject = null;
            String message = "";

            super.onPostExecute(s);

            if(s != null) { //응답 성공시
                try {
                    jsonObject = new JSONObject(s);
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                if (message.equals("Success to send push alram")) {
                Toast.makeText(getApplicationContext(), "참석신청이 완료되었습니다.", Toast.LENGTH_LONG).show();
                announce_detail_member_button.setText("참석 취소");
                new AnnounceDetailTask().execute(notice_id);

                if (message.equals("Already Exists")) {
                    Toast.makeText(getApplicationContext(), "이미 신청", Toast.LENGTH_LONG).show();
                }
//                }
            }
            else // s == null
            {
                if (message.equals("Already Exists")) {
                    Toast.makeText(getApplicationContext(), "이미 신청", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "참석하기에 실패하였습니다.", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    // 송금요청
    private class RequestMoneyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/account", PostString.requestMoney(strings[0]),"application/x-www-form-urlencoded");

            String response = null;

            try {
                response =  post.post();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject jsonObject = null;
            String message = "";

            if(s != null) { //응답 성공시
                try {
                    jsonObject = new JSONObject(s);
                    message = jsonObject.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.v("송송금금", message);
                if (message.equals("Success to send push alram")) {
                    Intent intent = new Intent(AnnounceDetailActivity.this, SendMoneyFinishActivity.class);
                    intent.putExtra("notice", notice);
                    intent.putExtra("position", 0); // 0이면 총무

                    startActivity(intent);
                }
            }
            else
            {

                Toast.makeText(getApplicationContext(), "송금요청에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }

    // 통신 (공지 상세보기)
    private class AnnounceDetailTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Get get = new Get("notice_id", notice_id);

            try {
                response = get.run("https://klean.apps.dev.clayon.io/api/club/notice/detail", "application/x-www-form-urlencoded");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {

            JSONObject dataObject = null;
            JSONArray notice_people = null;

            if (s == null) {
                return;
            }

            try {
                JSONObject jsonObject= new JSONObject(s);
                dataObject = jsonObject.getJSONObject("data");

                Log.v("데이따", dataObject.toString());
                notice_people = dataObject.getJSONArray("notice_people");

                int notice_category = dataObject.getInt("notice_category");

                // 설정
                announce_detail_announceTitle_text.setText(dataObject.getString("notice_title"));
                if (notice_category == 0) { // 0이면 일반공지
                    announce_detail_announceType_image.setImageResource(R.drawable.ic_normal_notice);
                }
                else if (notice_category == 1) { // 1이면 행사공지
                    announce_detail_announceType_image.setImageResource(R.drawable.ic_special_notice);
                }
                else {
                    announce_detail_announceType_image.setImageResource(R.drawable.ic_special_notice_grey);
                }

//                String managerImg = dataObject.getString("club_manager_img");

//                if (managerImg == null) {
                    Glide.with(getApplicationContext()).load(R.drawable.ic_profile_40).asBitmap().centerCrop().into(announce_detail_memberImage_circle); // 그룹 배경사진 설정
//                }
//                else {
//                    Glide.with(getApplicationContext()).load(dataObject.getString("club_manager_img")).asBitmap().centerCrop().into(announce_detail_memberImage_circle); // 그룹 배경사진 설정
//                }
                announce_detail_managerName_text.setText(dataObject.getString("club_manager"));

                String dateString = dataObject.getString("write_time");
                Log.v("writewrite", dateString);
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
                Date dateDD = transFormat.parse(dateString);

                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dateDD);
                int month = calendar.get(Calendar.MONTH) + 1;
                int date = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                String minuteStr = minute + "";

                if (minute == 0) {
                    minuteStr += "0";
                }

                //
                announce_detail_announceTime_text.setText(month + "/" + date + " " + hour + ":" + minuteStr);

                String noticeDateStr = dataObject.getString("notice_date");
                Log.v("noticenotice", noticeDateStr);
                Date dateDD2 = transFormat.parse(noticeDateStr);

                Calendar calendar2 = new GregorianCalendar();
                calendar2.setTime(dateDD2);

                int noticeMonth = calendar2.get(Calendar.MONTH) + 1;
                int noticeDate = calendar2.get(Calendar.DATE);
                int noticeYear = calendar2.get(Calendar.YEAR);

                announce_detail_date_text.setText(noticeYear + "." + noticeMonth + "." + noticeDate);
                announce_detail_time_text.setText(dataObject.getString("notice_time"));
                announce_detail_place_text.setText(dataObject.getString("notice_place"));
                announce_detail_announceContent_text.setText(dataObject.getString("notice_content"));

                int notice_cost = dataObject.getInt("notice_cost");
                announce_detail_money_text.setText(notice_cost + "");

                notice = new Notice(dataObject.getInt("notice_cost"), dataObject.getString("notice_title"), dataObject.getString("notice_date"), dataObject.getString("notice_time"), dataObject.getInt("notice_participant"));



            } catch (JSONException e) {
                Log.v("우엥", "오류오류,,1");
                e.printStackTrace();
                return;
            } catch (ParseException e) {
                Log.v("우엥", "오류오류,,2");
                e.printStackTrace();
            }

            super.onPostExecute(s);

            if (dataObject == null)
                return;
            if (notice_people == null)
                return;

            userList = new ArrayList<User>();

            // 받아온 response에서 searchGroupList에 group 추가
            for (int i = 0; i < notice_people.length(); i++) {
                try {
                    JSONObject jsonObject = notice_people.getJSONObject(i);
                    User user = new User(jsonObject.getString("user_name"), jsonObject.getString("image_uri"), jsonObject.getString("user_id"), jsonObject.getString("current_time"), jsonObject.getInt("account_check"), jsonObject.getString("user_deviceToken"));
                    userList.add(user);
                    Log.v("soominBB", "ㅇㅇㅇ");
                } catch (JSONException e) {
                    Log.v("soominBB", "ㅜㅜㅜ");
                    e.printStackTrace();
                }
            }


            announce_detail_joinNumber_text.setText(userList.size() + "");

            layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            announce_detail_join_recycler.setLayoutManager(layoutManager); // RecyclerView의 레이아웃 매니저 설정,,,
            memberListAdapter = new AdapterMemberList(getApplicationContext(), userList, user);
            announce_detail_join_recycler.setAdapter(memberListAdapter);
        }
    }
}
