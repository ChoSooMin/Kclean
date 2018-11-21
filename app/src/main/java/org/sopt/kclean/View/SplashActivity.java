package org.sopt.kclean.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;
import org.sopt.kclean.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int result = getIntent().getIntExtra("result", 0);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) { // 포그라운드일때,,?
            int temp = bundle.getInt("result");
            Log.d("TEMP", "TEMP : " + temp); // result값 받아짐

            if (temp == 1) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), SendMoneyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 0);
            }
            else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        }
        else {
            if (result == 0) { // 푸쉬알림 안눌
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            } else if (result == 1) { // 푸쉬알림 눌렀을 때
                Log.v("하하", "조수민 최고");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), SendMoneyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        }
    }
}