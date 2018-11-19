package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.sopt.kclean.R;
import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class SendMoneyActivity extends AppCompatActivity {

    private TextView send_money_title_text;
    private ImageView send_money_type_image;
    private Button send_money_send_btn; // 송금하기 버튼
    private CircleImageView send_money_profileImg;
    private TextView send_money_managerName_text;
    private TextView send_money_writeTime_text;
    private TextView send_money_date_text;
    private TextView send_money_time_text;
    private TextView send_money_place_text;
    private TextView send_money_content_text;
    private TextView send_money_money_text;
    private ImageButton close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        // xml
        send_money_title_text = (TextView) findViewById(R.id.send_money_title_text);
        send_money_type_image = (ImageView) findViewById(R.id.send_money_type_image);
        send_money_profileImg = (CircleImageView) findViewById(R.id.send_money_profileImg);
        send_money_managerName_text = (TextView) findViewById(R.id.send_money_managerName_text);
        send_money_writeTime_text = (TextView) findViewById(R.id.send_money_writeTime_text);
        send_money_date_text = (TextView) findViewById(R.id.send_money_date_text);
        send_money_time_text = (TextView) findViewById(R.id.send_money_time_text);
        send_money_place_text = (TextView) findViewById(R.id.send_money_place_text);
        send_money_content_text = (TextView) findViewById(R.id.send_money_content_text);
        send_money_money_text = (TextView) findViewById(R.id.send_money_money_text);
        close_btn = (ImageButton) findViewById(R.id.close_btn);
        send_money_send_btn = (Button) findViewById(R.id.send_money_send_btn); // 송금 버튼

        // xml

        close_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 송금하기
        send_money_send_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}