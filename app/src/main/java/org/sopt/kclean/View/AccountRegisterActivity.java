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
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;

import java.io.IOException;

public class AccountRegisterActivity extends AppCompatActivity {

    private EditText join_account_bank;
    private EditText join_account_account_text;
    private ToggleButton join_account_agree_toggleBtn;
    private ImageButton complete_btn;

    private User user;

    private boolean isChecking; // 동의 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        user = getIntent().getParcelableExtra("user");

        // xml
        join_account_bank = (EditText) findViewById(R.id.join_account_bank);
        join_account_account_text = (EditText) findViewById(R.id.join_account_account_text);
        join_account_agree_toggleBtn = (ToggleButton) findViewById(R.id.join_account_agree_toggleBtn);
        complete_btn = (ImageButton) findViewById(R.id.complete_btn);
        // xml

        // 토글 확인
        join_account_agree_toggleBtn.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                    isChecking = true;
                else
                    isChecking =false;
            }
        });

        // 완료 버튼
        complete_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isChecking) {
                    new RegisterAccountTask().execute(join_account_account_text.getText().toString(), join_account_bank.getText().toString());
                }
            }
        });
    }

    // 통신 (계좌 연동)
    private class RegisterAccountTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/user/account", PostString.registerAccount(strings[0], strings[1]), user.getToken(),"application/x-www-form-urlencoded");

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
                    String message = jsonObject.getString("message");

                    if (message.equals("success to make account")) {
                        Toast.makeText(getApplicationContext(), "계좌가 등록되었습니다.", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "계좌 등록이 실패했습니다.", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}
