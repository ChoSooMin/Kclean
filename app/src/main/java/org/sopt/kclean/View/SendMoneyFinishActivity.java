package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Notice;
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
    private TextView send_money_finish_money_text;
    private RelativeLayout relative3;

    private Notice notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money_finish);

        notice = getIntent().getParcelableExtra("notice");
        position = getIntent().getIntExtra("position", -1);
        Log.v("sendmoney", position + "" );

        // xml
        send_money_finish_money_text = (TextView) findViewById(R.id.send_money_finish_money_text);
        send_money_finish_type_text = (TextView) findViewById(R.id.send_money_finish_type_text);
        send_money_finish_title_text = (TextView) findViewById(R.id.send_money_finish_title_text);
        send_money_finish_date_text = (TextView) findViewById(R.id.send_money_finish_date_text);
        send_money_finish_time_text = (TextView) findViewById(R.id.send_money_finish_time_text);
        send_money_finish_number_text = (TextView) findViewById(R.id.send_money_finish_number_text);
        send_money_finish_success_btn = (Button) findViewById(R.id.send_money_finish_success_btn);
        relative3 = (RelativeLayout) findViewById(R.id.relative3);
        // xml

        send_money_finish_money_text.setText(notice.getNotice_cost());
        send_money_finish_title_text.setText(notice.getNotice_title());
        send_money_finish_date_text.setText(notice.getNotice_date());
        send_money_finish_time_text.setText(notice.getNotice_time());
        send_money_finish_number_text.setText(notice.getNotice_participant());

        if (position == 0) { // 총무일 때
            send_money_finish_type_text.setText("요청");
        }
        else if (position == 1) { // 일반회원일 때
            send_money_finish_type_text.setText("완료");
            relative3.setVisibility(View.GONE);
        }

        send_money_finish_success_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
