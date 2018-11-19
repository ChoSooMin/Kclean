package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.sopt.kclean.R;

public class SendMoneyFinishActivity extends AppCompatActivity {

    private int position;

    // xml
    private TextView send_money_finish_type_text;
    private TextView send_money_finish_title_text;
    private TextView send_money_finish_date_text;
    private TextView send_money_finish_time_text;
    private TextView send_money_finish_number_text;
    private Button send_money_finish_success_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money_finish);

        position = getIntent().getIntExtra("position", -1);
        Log.v("sendmoney", position + "" );

        // xml
        send_money_finish_type_text = (TextView) findViewById(R.id.send_money_finish_type_text);
        send_money_finish_title_text = (TextView) findViewById(R.id.send_money_finish_title_text);
        send_money_finish_date_text = (TextView) findViewById(R.id.send_money_finish_date_text);
        send_money_finish_time_text = (TextView) findViewById(R.id.send_money_finish_time_text);
        send_money_finish_number_text = (TextView) findViewById(R.id.send_money_finish_number_text);
        send_money_finish_success_btn = (Button) findViewById(R.id.send_money_finish_success_btn);
        // xml

        if (position == 0) { // 총무일 때
            send_money_finish_type_text.setText("요청");
        }
        else if (position == 1) { // 일반회원일 때
            send_money_finish_type_text.setText("완료");
        }
    }
}
