package org.sopt.kclean.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.sopt.kclean.Controller.ImageHandler;
import org.sopt.kclean.R;
import org.sopt.kclean.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class CreateGroupActivity extends AppCompatActivity {


    // 동아리 공개 여부 flag
    int isPublic = 1; // 1이면 공개, -1이면 비공개
    //앨범에서 사진 가져오기
    public static final int PICK_FROM_ALBUM = 1;

    private ImageButton create_group_complete_button;
    private ImageButton create_group_groupBackground_button;
    private EditText create_group_groupName_editTxt;
    private CircleImageView create_group_groupImage_circleView;
    private EditText create_group_groupDetail_editTxt;

    private static final String[] IMAGE_PROJECTION = {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.Thumbnails.DATA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        // xml이랑 연결 우히히
        create_group_complete_button = (ImageButton) findViewById(R.id.create_group_complete_button);
        create_group_groupBackground_button = (ImageButton) findViewById(R.id.create_group_groupBackground_button);
        create_group_groupName_editTxt = (EditText) findViewById(R.id.create_group_groupName_editTxt);
        create_group_groupImage_circleView = (CircleImageView) findViewById(R.id.create_group_groupImage_circleView);
        create_group_groupDetail_editTxt = (EditText) findViewById(R.id.create_group_groupDetail_editTxt);

        //배경 사진 고르기
        create_group_groupBackground_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                //   intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

                {

                    checkVerify();
                } else

                {

                    startActivityForResult(intent, PICK_FROM_ALBUM);

                }


            }
        });


        //완료 버튼
        create_group_complete_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String status = "ok";//전송 결과 상태
                //서버랑 통신해서 동아리 정보 보냄 api 확정시 코드 추가


                //
                if (status.compareTo("ok") == 0) {
                    // 다이얼로그 바디
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CreateGroupActivity.this, R.style.MyAlertDialogStyle);
                    // 메세지
                    alert_confirm.setMessage("동아리 가입이 성공적으로 완료 되었습니다");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });

                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();
                    // 아이콘
                    //alert.setIcon(R.drawable.ic_launcher);
                    // 다이얼로그 타이틀
                    // 다이얼로그 보기
                    if (isFinishing() == false) {

                        alert.show();

                    }

                } else {


                    // 다이얼로그 바디
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CreateGroupActivity.this, R.style.MyAlertDialogStyle);
                    // 메세지
                    alert_confirm.setMessage("동아리 가입이 실패하였습니다.");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", null);
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();
                    // 아이콘
                    //alert.setIcon(R.drawable.ic_launcher);
                    // 다이얼로그 타이틀
                    // 다이얼로그 보기
                    if (isFinishing() == false) {

                        alert.show();

                    }


                }

            }
        });


    }


    //사진 관련해서 return 값 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "" + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_FROM_ALBUM:
                    Toast.makeText(this, "" + 123, Toast.LENGTH_SHORT).show();
                    ImageHandler.sendPicture(data.getData(),this,create_group_groupBackground_button); //갤러리에서 가져오기
                    break;

            }
        }


    }


    //권한 체크
    public void checkVerify() {
        if (
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // ...
            }
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);

            //   intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_FROM_ALBUM);
        }
    }

}