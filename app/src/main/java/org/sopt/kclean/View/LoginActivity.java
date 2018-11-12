package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText login_id_editTxt; // 아이디
    private EditText login_pw_editTxt; // 비밀번호
    private Button login_login_button; // 로그인 버튼
    private Button login_join_button; // 회원가입 버튼
    private User user; //로그인 성공시 정보 받기


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id_editTxt = (EditText) findViewById(R.id.login_id_editTxt); // 아이디
        login_pw_editTxt = (EditText) findViewById(R.id.login_pw_editTxt); // 비밀번호
        login_login_button = (Button) findViewById(R.id.login_login_button); // 로그인 버튼
        login_join_button = (Button) findViewById(R.id.login_join_button); // 회원가입 버튼

        // 로그인 리스너
        login_login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(login_id_editTxt.getText() == null)
                {


                }
                else if(login_pw_editTxt.getText() == null){


                }
                else
                {
                    LoginTask loginTask = new LoginTask();
                    loginTask.execute(login_id_editTxt.getText().toString(),login_pw_editTxt.getText().toString());

                }




            }
        });

        // 회원가입 리스너
        login_join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //회원가입 액티비티
                //Intent intent = new Intent(LoginActivity,)




            }
        });
    }


    private class LoginTask extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/signin", PostString.signinJson(strings[0],strings[1],strings[2]));
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
                    user.setToken(jsonObject.getString("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //(1)인텐트로 user 넘기기
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);


            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                login_id_editTxt.setText("");
                login_pw_editTxt.setText("");
            }

            super.onPostExecute(s);
        }
    }
}

