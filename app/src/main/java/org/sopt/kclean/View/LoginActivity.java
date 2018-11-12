package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.sopt.kclean.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login_id_editTxt; // 아이디
    private EditText login_pw_editTxt; // 비밀번호
    private Button login_login_button; // 로그인 버튼
    private Button login_join_button; // 회원가입 버튼


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

            }
        });

        // 회원가입 리스너
        login_join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}
