package org.sopt.kclean.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Firebase.FireBaseHandler;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;

import static android.support.v4.media.session.MediaButtonReceiver.handleIntent;

public class LoginActivity extends AppCompatActivity {

    private EditText login_id_editTxt; // 아이디
    private EditText login_pw_editTxt; // 비밀번호
    private Button login_login_button; // 로그인 버튼
    private ImageButton login_join_button; // 회원가입 버튼
    private ToggleButton login_auto_toggleBtn;
    private boolean isChecking; //자동 로그인 확인
    private User user; //로그인 성공시 정보 받기
    private String device_token;
    private int goingSendMoney ; //1이면 바로 송금
    SharedPreferences pref;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        goingSendMoney = intent.getIntExtra("result",0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id_editTxt = (EditText) findViewById(R.id.login_id_editTxt); // 아이디
        login_pw_editTxt = (EditText) findViewById(R.id.login_pw_editTxt); // 비밀번호
        login_login_button = (Button) findViewById(R.id.login_login_button); // 로그인 버튼
        login_join_button = (ImageButton) findViewById(R.id.login_join_button); // 회원가입 버튼
        login_auto_toggleBtn = (ToggleButton)findViewById(R.id.login_auto_toggleBtn); //토글 버튼

        user = new User();
        //자동로그인 저장 정보
        pref = getSharedPreferences("user_info", MODE_PRIVATE);
        user.setId(pref.getString("id",""));
        user.setPassword(pref.getString("password",""));
        Log.d("save",user.getId()+"    "+user.getPassword());

        if(user.getId() != "" && user.getPassword() != "")
        {
            LoginTask loginTask = new LoginTask();
            user.setToken(FireBaseHandler.passPushTokenToServer(user.getId()));
            loginTask.execute(user.getId(),user.getToken(),user.getPassword());
            return;
        }


        // 로그인 리스너
        login_login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(login_id_editTxt.getText().toString().equals(""))
                {
                    DialogCustom customDialog = new DialogCustom(LoginActivity.this);
                    // 커스텀 다이얼로그를 호출한다.
                    // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                    customDialog.callFunction("아이디를 입력해주세요.");
                }
                else if(login_pw_editTxt.getText().toString().equals("")){
                    DialogCustom customDialog = new DialogCustom(LoginActivity.this);
                    // 커스텀 다이얼로그를 호출한다.
                    // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                    customDialog.callFunction("비밀번호를 입력해주세요.");
                }
                else
                {
                    LoginTask loginTask = new LoginTask();
                    user.setToken(FireBaseHandler.passPushTokenToServer(login_id_editTxt.getText().toString()));
                    loginTask.execute(login_id_editTxt.getText().toString(), user.getToken() ,login_pw_editTxt.getText().toString());

                }

            }
        });

        // 회원가입 리스너
        login_join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //회원가입 액티비티
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);

                startActivity(intent);
            }
        });

        login_auto_toggleBtn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                    isChecking = true;
                else
                    isChecking =false;
            }
        });
    }

    private class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/signin", PostString.signinJson(strings[0],strings[1],strings[2]),"application/x-www-form-urlencoded");
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
                    if(isChecking ==true) //자동로그인 저장
                    {
                        pref = getSharedPreferences("user_info", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();//저장하려면 editor가 필요
                        String str = login_id_editTxt.getText().toString(); // 사용자가 입력한 값
                        editor.putString("id", str); // 입력
                        str = login_pw_editTxt.getText().toString(); // 사용자가 입력한 값
                        editor.putString("password", str); // 입력
                        editor.commit(); // 파일에 최종 반영함
                    }

                    jsonObject = new JSONObject(s);
                    //user.setToken();
                    Intent intent;
                    if(goingSendMoney == 0)
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    else
                        intent = new Intent(LoginActivity.this, SendMoneyActivity.class);
                    intent.putExtra("token", jsonObject.getString("token"));
                    Log.v("toktok", jsonObject.getString("token"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                login_id_editTxt.setText("");
                login_pw_editTxt.setText("");

                DialogCustom customDialog = new DialogCustom(LoginActivity.this);
                // 커스텀 다이얼로그를 호출한다.
                // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
                customDialog.callFunction("아이디 또는 비밀번호가 맞지 않습니다.");

            }

            super.onPostExecute(s);
        }
    }

}
