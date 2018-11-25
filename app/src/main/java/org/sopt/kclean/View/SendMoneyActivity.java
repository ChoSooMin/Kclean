package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.AdapterMemberList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Group;
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

public class SendMoneyActivity extends AppCompatActivity {

    private TextView send_money_title_text;
    private ImageView send_money_type_image;
    private Button send_money_send_btn; // 송금하기 버튼
    private CircleImageView send_money_profileImg;
    private TextView send_money_managerName_text;
    private TextView send_money_writeTime_text;
    private TextView send_money_date_text;
    private TextView send_money_time_text;
    private TextView send_money_place_text;
    private TextView send_money_content_text;
    private TextView send_money_money_text;
    private ImageButton close_btn;

    private User user;
    private String notice_id;
    private String notice_price;
    private Notice notice;

    String response = "";
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);


        user = getIntent().getParcelableExtra("user");
        notice_id = getIntent().getStringExtra("notice_id");
//        int price = getIntent().getIntExtra("notice_price", -1);
        notice_price = price + "";
        notice = getIntent().getParcelableExtra("notice");

        Log.v("noticenotice", "notice_id || " + notice_id);
        Log.v("noticenotice", "notice_price || " + notice_price);

        // xml
        send_money_title_text = (TextView) findViewById(R.id.send_money_title_text);
        send_money_type_image = (ImageView) findViewById(R.id.send_money_type_image);
        send_money_profileImg = (CircleImageView) findViewById(R.id.send_money_profileImg);
        send_money_managerName_text = (TextView) findViewById(R.id.send_money_managerName_text);
        send_money_writeTime_text = (TextView) findViewById(R.id.send_money_writeTime_text);
        send_money_date_text = (TextView) findViewById(R.id.send_money_date_text);
        send_money_time_text = (TextView) findViewById(R.id.send_money_time_text);
        send_money_place_text = (TextView) findViewById(R.id.send_money_place_text);
        send_money_content_text = (TextView) findViewById(R.id.send_money_content_text);
        send_money_money_text = (TextView) findViewById(R.id.send_money_money_text);
        close_btn = (ImageButton) findViewById(R.id.close_btn);
        send_money_send_btn = (Button) findViewById(R.id.send_money_send_btn); // 송금 버튼

        // xml

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); // 닫기 버튼,,

        new AnnounceDetailTask().execute(notice_id);

        // 송금하기
        send_money_send_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new SendMoneyTask().execute(notice_id, price + "");

                Intent intent = new Intent(getApplicationContext(), SendMoneyFinishActivity.class);
                intent.putExtra("position", 1);

                Toast.makeText(getApplicationContext(), "송금 완료", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
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

            if (s == null) {
                return;
            }

            try {
                JSONObject jsonObject= new JSONObject(s);
                dataObject = jsonObject.getJSONObject("data");

                notice = new Notice(dataObject.getInt("notice_cost"), dataObject.getString("notice_title"), dataObject.getString("notice_date"), dataObject.getString("notice_time"), dataObject.getInt("notice_participant"));

                Log.v("데이따", dataObject.toString());

                int notice_category = dataObject.getInt("notice_category");

                // 설정
                send_money_title_text.setText(notice.getNotice_title());
                if (notice_category == 0) { // 0이면 일반공지
                    send_money_type_image.setImageResource(R.drawable.ic_normal_notice);
                }
                else if (notice_category == 1) { // 1이면 행사공지
                    send_money_type_image.setImageResource(R.drawable.ic_special_notice);
                }
                else {
                    send_money_type_image.setImageResource(R.drawable.ic_special_notice_grey);
                }

//                String managerImg = dataObject.getString("club_manager_img");

//                if (managerImg == null) {
                Glide.with(getApplicationContext()).load(R.drawable.ic_profile_40).asBitmap().centerCrop().into(send_money_profileImg);
//                }
//                else {
//                    Glide.with(getApplicationContext()).load(dataObject.getString("club_manager_img")).asBitmap().centerCrop().into(announce_detail_memberImage_circle); // 그룹 배경사진 설정
//                }
                send_money_managerName_text.setText(dataObject.getString("club_manager"));


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
                send_money_writeTime_text.setText(month + "/" + date + " " + hour + ":" + minuteStr);

                String noticeDateStr = notice.getNotice_date();
                Log.v("noticenotice", noticeDateStr);
                Date dateDD2 = transFormat.parse(noticeDateStr);

                Calendar calendar2 = new GregorianCalendar();
                calendar2.setTime(dateDD2);

                int noticeMonth = calendar2.get(Calendar.MONTH) + 1;
                int noticeDate = calendar2.get(Calendar.DATE);
                int noticeYear = calendar2.get(Calendar.YEAR);

                send_money_date_text.setText(noticeYear + "." + noticeMonth + "." + noticeDate);
                send_money_time_text.setText(notice.getNotice_time());
                send_money_place_text.setText(dataObject.getString("notice_place"));
                send_money_content_text.setText(dataObject.getString("notice_content"));

                price = notice.getNotice_cost();
                send_money_money_text.setText(price + "");
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
        }
    }

    // 통신 (송금)
    private class SendMoneyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/account/transfer", PostString.sendMoney(strings[0], Integer.parseInt(strings[1])), user.getToken(),"application/x-www-form-urlencoded");

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

            if(s != null) { //응답 성공시
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.v("sensend", jsonObject.toString());

                // 송금 완료하면
                Toast.makeText(getApplicationContext(), "송금 완료.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SendMoneyActivity.this, SendMoneyFinishActivity.class);
                intent.putExtra("position", 1);
                intent.putExtra("notice", notice);

                startActivity(intent);
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                Toast.makeText(getApplicationContext(), "송금 실패했습니다.", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}