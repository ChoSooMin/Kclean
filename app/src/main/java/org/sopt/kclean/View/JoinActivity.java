package org.sopt.kclean.View;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.R;

import java.io.IOException;

import okhttp3.Response;

public class JoinActivity extends AppCompatActivity {

    private EditText join_id_editTxt; // 아이디 EditText
    private EditText join_name_editTxt; // 이름 EditText
    private EditText join_pw_editTxt; // 비밀번호 EditText
    private EditText join_phone_editTxt; // 핸드폰번호 EditText
    private EditText join_gender_editTxt; // 성별 EditText
    private EditText join_birth_editTxt; // 생년월일 EditText
    private EditText join_univ_editTxt; // 대학교 EditText
    private EditText join_major_editTxt; // 전공 EditText
    private Button join_join_button; // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // xml
        join_id_editTxt = (EditText) findViewById(R.id.join_id_editTxt);
        join_name_editTxt = (EditText) findViewById(R.id.join_name_editTxt);
        join_pw_editTxt = (EditText) findViewById(R.id.join_pw_editTxt);
        join_phone_editTxt = (EditText) findViewById(R.id.join_phone_editTxt);
        join_gender_editTxt = (EditText) findViewById(R.id.join_gender_editTxt);
        join_birth_editTxt = (EditText) findViewById(R.id.join_birth_editTxt);
        join_univ_editTxt = (EditText) findViewById(R.id.join_univ_editTxt);
        join_major_editTxt = (EditText) findViewById(R.id.join_major_editTxt);
        join_join_button = (Button) findViewById(R.id.join_join_button);

        // 회원가입 버튼 리스너
        join_join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 통신
                JoinTask joinTask = new JoinTask();

                if (join_id_editTxt.getText().toString() == null || join_name_editTxt.getText().toString() == null || join_pw_editTxt.getText().toString() == null || join_phone_editTxt.getText().toString() == null || join_gender_editTxt.getText().toString() == null || join_birth_editTxt.getText().toString() == null || join_univ_editTxt.getText().toString() == null || join_major_editTxt.getText().toString() == null) {
                    // 다이얼로그 바디
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(JoinActivity.this, R.style.MyAlertDialogStyle);
                    // 메세지
                    alert_confirm.setMessage("정보를 모두 입력해주세요.");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", null);
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();
                    // 아이콘
                    //alert.setIcon(R.drawable.ic_launcher);
                    // 다이얼로그 타이틀
                    // 다이얼로그 보기
                    alert.show();
                }
                else {
                    joinTask.execute(join_id_editTxt.getText().toString(), join_name_editTxt.getText().toString(), join_pw_editTxt.getText().toString(), join_phone_editTxt.getText().toString(), join_gender_editTxt.getText().toString(), join_birth_editTxt.getText().toString(), join_univ_editTxt.getText().toString(), join_major_editTxt.getText().toString());
                }
            }
        });
    }

    private class JoinTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/signup", PostString.signupJson(strings[0], strings[1], strings[2], strings[3], Integer.parseInt(strings[4]), strings[5], strings[6], strings[7]));

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

                // 회원가입 성공하면 로그인 화면으로 보내깅 ㅋ
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);

                startActivity(intent);
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                Log.v("soomin", "회원가입 실패했당");
            }

            super.onPostExecute(s);
        }
    }
}
