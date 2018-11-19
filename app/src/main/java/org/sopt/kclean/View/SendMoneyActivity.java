package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.sopt.kclean.R;

public class SendMoneyActivity extends AppCompatActivity {

    private Button send_money_send_btn; // 송금하기 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        send_money_send_btn = (Button) findViewById(R.id.send_money_send_btn); // 송금 버튼
        send_money_send_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}