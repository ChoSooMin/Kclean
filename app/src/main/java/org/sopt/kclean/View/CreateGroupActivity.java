package org.sopt.kclean.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.sopt.kclean.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateGroupActivity extends AppCompatActivity {


    private Button create_group_complete_button;
    private Button create_group_groupBackground_button;
    private EditText create_group_groupName_editTt;
    private CircleImageView create_group_groupImage_circleView;
    private Button create_group_publish_button;
    private EditText create_group_groupPW_editTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        create_group_complete_button = (Button)findViewById(R.id.create_group_complete_button);
        create_group_groupBackground_button = (Button)findViewById(R.id.create_group_groupBackground_button);
        create_group_groupName_editTt = (EditText)findViewById(R.id.create_group_groupName_editTt);
        create_group_groupImage_circleView = (CircleImageView)findViewById(R.id.create_group_groupImage_circleView);
        create_group_publish_button = (Button)findViewById(R.id.create_group_group_publish_button);
        create_group_groupPW_editTxt = (EditText)findViewById(R.id.create_group_groupPW_editTxt);

    }
}
