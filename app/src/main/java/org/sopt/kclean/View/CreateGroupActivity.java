package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.sopt.kclean.R;

import de.hdodenhof.circleimageview.*;


public class CreateGroupActivity extends AppCompatActivity {


    // 동아리 공개 여부 flag
    int isPublic = 1; // 1이면 공개, -1이면 비공개


    private Button create_group_complete_button;
    private Button create_group_groupBackground_button;
    private EditText create_group_groupName_editTt;
    private CircleImageView create_group_groupImage_circleView;
    private Button create_group_publish_button;
    private EditText create_group_groupPW_editTxt;
    private LinearLayout create_group_groupPW_linear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        // xml이랑 연결 우히히
        create_group_complete_button = (Button)findViewById(R.id.create_group_complete_button);
        create_group_groupBackground_button = (Button)findViewById(R.id.create_group_groupBackground_button);
        create_group_groupName_editTt = (EditText)findViewById(R.id.create_group_groupName_editTt);
        create_group_groupImage_circleView = (CircleImageView)findViewById(R.id.create_group_groupImage_circleView);
        create_group_publish_button = (Button)findViewById(R.id.create_group_group_publish_button);
        create_group_groupPW_editTxt = (EditText)findViewById(R.id.create_group_groupPW_editTxt);
        create_group_groupPW_linear = (LinearLayout) findViewById(R.id.create_group_groupPW_linear);

        // 버튼 리스너 우히히
        create_group_publish_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isPublic == 1) {
                    // 비공개로
                    create_group_groupPW_linear.setVisibility(View.VISIBLE);
                    isPublic = -1;
                } else if (isPublic == -1) {
                    // 공개로
                    create_group_groupPW_linear.setVisibility(View.GONE);
                    isPublic = 1;
                }
            }
        });
    }
}
