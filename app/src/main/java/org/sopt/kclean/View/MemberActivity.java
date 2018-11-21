package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.sopt.kclean.R;

public class MemberActivity extends AppCompatActivity {

    private TextView member_totalNumber_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        String totalNumber = getIntent().getStringExtra("totalNumber");
        member_totalNumber_text = (TextView) findViewById(R.id.member_totalNumber_text);

        member_totalNumber_text.setText(totalNumber);
    }
}
