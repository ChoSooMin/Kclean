package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;

public class WriteAnnounceActivity extends AppCompatActivity {

    // 앞에서 받아올 애들,,,
    private User user;
    private Group group;

    // xml
    private EditText write_board_title_editTxt; // 공지 제목
    private RadioGroup write_board_type_radioGroup; // 공지 유형 라됴 그룹
    private RadioButton write_board_type_normal_radioBtn; // 공지 유형 (일반) 라됴 버튼
    private RadioButton write_board_type_special_radioBtn; // 공지 유형 (행사) 라됴 버튼
    private EditText write_board_personMoney_editTxt; // 1인당 요청 금액
    private EditText write_board_place_editTxt; // 장소
    private EditText write_board_date_editTxt; // 날짜
    private EditText write_board_time_editTxt; // 시간
    private EditText write_board_content_editTxt; // 공지 내용
    private ImageButton write_board_complete_imgBtn; // 완료 버튼
    private LinearLayout write_board_requestMoney_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_announce);

        user = getIntent().getParcelableExtra("user"); // user 객체 받아오기
        group = getIntent().getParcelableExtra("group"); // group 객체 받아오기
        final String club_id = group.getGroupId();

        write_board_title_editTxt = (EditText) findViewById(R.id.write_board_title_editTxt);
        write_board_type_radioGroup = (RadioGroup) findViewById(R.id.write_board_type_radioGroup);
        write_board_type_normal_radioBtn = (RadioButton) findViewById(R.id.write_board_type_normal_radioBtn);
        write_board_type_special_radioBtn = (RadioButton) findViewById(R.id.write_board_type_special_radioBtn);
        write_board_personMoney_editTxt = (EditText) findViewById(R.id.write_board_personMoney_editTxt);
        write_board_place_editTxt = (EditText) findViewById(R.id.write_board_place_editTxt);
        write_board_date_editTxt = (EditText) findViewById(R.id.write_board_date_editTxt);
        write_board_time_editTxt = (EditText) findViewById(R.id.write_board_time_editTxt);
        write_board_content_editTxt = (EditText) findViewById(R.id.write_board_content_editTxt);
        write_board_complete_imgBtn = (ImageButton) findViewById(R.id.write_board_complete_imgBtn);
        write_board_requestMoney_linear = (LinearLayout) findViewById(R.id.write_board_requestMoney_linear);

        write_board_type_normal_radioBtn.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    write_board_requestMoney_linear.setVisibility(View.GONE);
                }
                else {
                    write_board_requestMoney_linear.setVisibility(View.VISIBLE);
                }
            }
        });

        // 완료 버튼 리스너
        write_board_complete_imgBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String notice_category = "-1";

                if (write_board_type_normal_radioBtn.isChecked()) {
                    Log.v("noticenotice", "일반");
                    notice_category = "0";
                }
                else if (write_board_type_special_radioBtn.isChecked()){
                    Log.v("noticenotice", "행사");
                    notice_category = "1";
                }

                new WriteAnnounceTask().execute(club_id, write_board_title_editTxt.getText().toString(), notice_category, write_board_place_editTxt.getText().toString(), write_board_date_editTxt.getText().toString(), write_board_time_editTxt.getText().toString(), write_board_content_editTxt.getText().toString(), write_board_personMoney_editTxt.getText().toString());
            }
        });
    }

    // 통신 (공지 작성)
    private class WriteAnnounceTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/club/notice/add", PostString.writeAnnounce(strings[0], strings[1], Integer.parseInt(strings[2]), strings[3], strings[4], strings[5], strings[6], strings[7]), user.getToken(),"application/x-www-form-urlencoded");

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

                if (message.equals("Success to write notice(event)")) {
                    Toast.makeText(getApplicationContext(), "공지를 등록하였습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                Toast.makeText(getApplicationContext(), "공지 등록에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}
