package org.sopt.kclean.View;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.R;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Response;

public class JoinActivity extends AppCompatActivity {

    private EditText join_id_editTxt; // 아이디 EditText
    private EditText join_pw_editTxt; // 비밀번호 EditText
    private EditText join_pwAgain_editTxt; // 비밀번호 확인 EditText
    private RelativeLayout join_pwCheck_layout; // 비밀번호 체크 RelativeLayout
    private EditText join_name_editTxt; // 이름 EditText
    private EditText join_birth_editTxt; // 생년월일 EditText
    private RadioGroup join_gender_radioGroup; // 성별 RadioGroup
    private RadioButton join_woman_radioBtn; // 여자 RadioButton
    private RadioButton join_man_radioBtn; // 남자 RadioButton
    private EditText join_phone_editTxt; // 핸드폰번호 EditText
    private EditText join_univ_editTxt; // 대학교 EditText
    private EditText join_major_editTxt; // 전공 EditText
    private Button join_join_button; // 회원가입 버튼

    // 핸드폰 번호 형식 확인
    private String phoneFormat = "^\\s*(010|011|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
    private Pattern phonePattern = Pattern.compile(phoneFormat);

    // 생년월일 형식 확인
    private String birthFormat = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$";
    private Pattern birthPattern = Pattern.compile(birthFormat);

    // 성별 체크
    int gender = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // xml
        join_id_editTxt = (EditText) findViewById(R.id.join_id_editTxt); // 아이디
        join_pw_editTxt = (EditText) findViewById(R.id.join_pw_editTxt); // 비밀번호
        join_pwAgain_editTxt = (EditText) findViewById(R.id.join_pwAgain_editTxt); // 비밀번호 확인
        join_pwCheck_layout = (RelativeLayout) findViewById(R.id.join_pwCheck_layout); // 비밀번호 체크 레이아웃
        join_name_editTxt = (EditText) findViewById(R.id.join_name_editTxt); // 이름
        join_birth_editTxt = (EditText) findViewById(R.id.join_birth_editTxt); // 생년월일
        join_gender_radioGroup = (RadioGroup) findViewById(R.id.join_gender_radioGroup); // 성별 라디오그룹
        join_woman_radioBtn = (RadioButton) findViewById(R.id.join_woman_radioBtn); // 여자 RadioBUtton
        join_man_radioBtn = (RadioButton) findViewById(R.id.join_man_radioBtn); // 남자 radioButton
        join_phone_editTxt = (EditText) findViewById(R.id.join_phone_editTxt); // 핸드폰번호
        join_univ_editTxt = (EditText) findViewById(R.id.join_univ_editTxt); // 대학교
        join_major_editTxt = (EditText) findViewById(R.id.join_major_editTxt); // 학과
        join_join_button = (Button) findViewById(R.id.join_join_button); // 회원가입 버튼

        join_pwAgain_editTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((join_pw_editTxt.getText().toString()).equals(join_pwAgain_editTxt.getText().toString())) {
                    join_pwCheck_layout.setVisibility(View.GONE);
                } else {
                    join_pwCheck_layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        join_gender_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.join_woman_radioBtn) {
                    gender = 0;
                }
                else if (checkedId == R.id.join_man_radioBtn) {
                    gender = 1;
                }
            }
        });

        // 회원가입 버튼 리스너
        join_join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 통신
                JoinTask joinTask = new JoinTask();

                if (join_id_editTxt.getText().toString().equals("") || join_name_editTxt.getText().toString().equals("") || join_pw_editTxt.getText().toString().equals("") || join_phone_editTxt.getText().toString().equals("") || join_birth_editTxt.getText().toString().equals("") || join_univ_editTxt.getText().toString().equals("") || join_major_editTxt.getText().toString().equals("")) {
                    // 다이얼로그 바디
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(JoinActivity.this, R.style.MyAlertDialogStyle);
                    // 메세지
                    alert_confirm.setMessage("정보를 모두 입력해주세요.");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", null);
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();
                    // 아이콘
//                     alert.setIcon(R.drawable.ic_launcher);
                    // 다이얼로그 타이틀
                    // 다이얼로그 보기
                    alert.show();
                }
                else {

                    Matcher phoneMatcher = phonePattern.matcher(join_phone_editTxt.getText().toString());
                    Matcher birthMatcher = birthPattern.matcher(join_birth_editTxt.getText().toString());

                    if (!phoneMatcher.matches()) {
                        // 다이얼로그 바디
                        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(JoinActivity.this, R.style.MyAlertDialogStyle);
                        // 메세지
                        alert_confirm.setMessage("전화번호 형식이 맞지 않습니다.");
                        // 확인 버튼 리스너
                        alert_confirm.setPositiveButton("확인", null);
                        // 다이얼로그 생성
                        AlertDialog alert = alert_confirm.create();
                        // 아이콘
//                     alert.setIcon(R.drawable.ic_launcher);
                        // 다이얼로그 타이틀
                        // 다이얼로그 보기
                        alert.show();
                    }
                    else if (!birthMatcher.matches()) {
                        // 다이얼로그 바디
                        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(JoinActivity.this, R.style.MyAlertDialogStyle);
                        // 메세지
                        alert_confirm.setMessage("생년월일 형식이 맞지 않습니다.");
                        // 확인 버튼 리스너
                        alert_confirm.setPositiveButton("확인", null);
                        // 다이얼로그 생성
                        AlertDialog alert = alert_confirm.create();
                        // 아이콘
//                     alert.setIcon(R.drawable.ic_launcher);
                        // 다이얼로그 타이틀
                        // 다이얼로그 보기
                        alert.show();
                    }
                    else {
                        joinTask.execute(join_id_editTxt.getText().toString(), join_pw_editTxt.getText().toString(), join_name_editTxt.getText().toString(), join_birth_editTxt.getText().toString(), "" + gender, join_phone_editTxt.getText().toString(), join_univ_editTxt.getText().toString(), join_major_editTxt.getText().toString());
                    }
                }
            }
        });
    }

    private class JoinTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/signup", PostString.signupJson(strings[0], strings[1], strings[2], strings[3], Integer.parseInt(strings[4]), strings[5], strings[6], strings[7]),"application/x-www-form-urlencoded");

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
                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
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
