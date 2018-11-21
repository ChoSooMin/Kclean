package org.sopt.kclean.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.AdapterGroupList;
import org.sopt.kclean.Controller.Get;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.Group;
import org.sopt.kclean.Model.Notice;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

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

    private User user;
    private String notice_id;
    private String notice_price;
    private Notice notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        user = getIntent().getParcelableExtra("user");
        notice_id = getIntent().getStringExtra("notice_id");
        int price = getIntent().getIntExtra("notice_price", -1);
        notice_price = price + "";
        notice = getIntent().getParcelableExtra("notice");

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
                new SendMoneyTask().execute(notice_id, notice_price);
            }
        });
    }

    // 통신(송금)
    private class SendMoneyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/account/transfer", PostString.sendMoney(strings[0], Integer.parseInt(strings[1])), user.getToken(),"application/x-www-form-urlencoded");

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // 회원가입 성공하면 로그인 화면으로 보내깅 ㅋ
                Toast.makeText(getApplicationContext(), "송금 완료.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SendMoneyActivity.this, SendMoneyFinishActivity.class);
                intent.putExtra("position", 1);

                startActivity(intent);
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
                Toast.makeText(getApplicationContext(), "송금 실패했습니다.", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}