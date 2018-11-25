package org.sopt.kclean.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Firebase.FireBaseHandler;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;


public class SplashActivity extends AppCompatActivity {

    int result;
    private User user; //로그인 성공시 정보 받기
    SharedPreferences pref;
    private String  notice_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            result = bundle.getInt("result");
            notice_id = bundle.getString("notice_id");
        }
        user = new User();
        //자동로그인 저장 정보
        pref = getSharedPreferences("user_info", MODE_PRIVATE);
        user.setId(pref.getString("id", ""));
        user.setPassword(pref.getString("password", ""));
        Log.d("save", user.getId() + "    " + user.getPassword());

        if (user.getId() != "" && user.getPassword() != "") {
            SplashTask loginTask = new SplashTask();
            user.setToken(FireBaseHandler.passPushTokenToServer(user.getId()));
            loginTask.execute(user.getId(), user.getToken(), user.getPassword());
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();

    }

    private class SplashTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/signin", PostString.signinJson(strings[0], strings[1], strings[2]), "application/x-www-form-urlencoded");
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
            super.onPostExecute(s);
            JSONObject jsonObject = null;
            if (s != null) { //응답 성공시
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //user.setToken();
                Intent intent;
                if (result == 0)
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                else {
                    intent = new Intent(SplashActivity.this, SendMoneyActivity.class);
                    intent.putExtra("notice_id",notice_id);
                    intent.putExtra("user",user);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try {
                    intent.putExtra("token", jsonObject.getString("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
                finish();
            }
        }
    }

}