package org.sopt.kclean.View;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sopt.kclean.Model.Group;
import org.sopt.kclean.R;

public class GroupBeforeJoinActivity extends AppCompatActivity {

    ImageView group_before_groupBackground;
    ImageView group_before_groupImage;
    TextView group_before_groupName;
    TextView group_before_groupTotalMember;
    TextView group_before_groupMaster;
    Button group_before_plusBtn;
    TextView group_before_groupDetail;
    Button group_before_joinBtn;
    RelativeLayout group_before_detailView;

    // 더보기 flag
    int detail = -1; // -1일 때는 더보기 안나오고 1일 때는 더보기 나오게

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_before_join);

        group_before_groupBackground = (ImageView) findViewById(R.id.group_before_groupBackground);
        group_before_groupImage = (ImageView) findViewById(R.id.group_before_groupImage);
        group_before_groupName = (TextView) findViewById(R.id.group_before_groupName);
        group_before_groupTotalMember = (TextView) findViewById(R.id.group_before_groupTotalMember);
        group_before_groupMaster = (TextView) findViewById(R.id.group_before_groupMaster);
        group_before_plusBtn = (Button) findViewById(R.id.group_before_plusBtn);
        group_before_groupDetail = (TextView) findViewById(R.id.group_before_groupDetail);
        group_before_joinBtn = (Button) findViewById(R.id.group_before_joinBtn);
        group_before_detailView = (RelativeLayout) findViewById(R.id.group_before_detailView);

        Intent intent = getIntent();
        Group group = (Group) intent.getSerializableExtra("selectedGroup");

        //이미지는 URL로 가져온다.


        //group_before_groupBackground.setImageDrawable(group.getGroupBackground());
        //group_before_groupImage.setImageDrawable(group.getGroupImage());
        group_before_groupName.setText(group.getGroupName());
        group_before_groupTotalMember.setText(group.getTotalMember());
        group_before_groupMaster.setText(group.getMasterName());
        group_before_groupDetail.setText(group.getGroupDetail());

//        group_before_groupBackground.setImageDrawable(getDrawable(R.drawable.sopt));
//        group_before_groupImage.setImageDrawable(getDrawable(R.drawable.sopt));
//        group_before_groupName.setText("SOPT 23");
//        group_before_groupTotalMember.setText("" + 435);
//        group_before_groupMaster.setText("류지훈");
//        group_before_groupDetail.setText("최효진 최고");

        // 버튼 리스너
        group_before_plusBtn.setOnClickListener(new View.OnClickListener() { // 더보기 버튼
            @Override
            public void onClick(View v) {
                if (detail == -1) { // 더보기 나타내기
                    group_before_detailView.setVisibility(View.VISIBLE);
                    detail = 1;
                }
                else if (detail == 1) { // 더보기 안보이기
                    group_before_detailView.setVisibility(View.GONE);
                    detail = -1;
                }
            }
        });

        group_before_joinBtn.setOnClickListener(new View.OnClickListener() { // 가입하기 버튼
            @Override
            public void onClick(View v) {

            }
        });
    }
}
