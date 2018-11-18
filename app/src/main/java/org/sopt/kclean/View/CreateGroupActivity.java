package org.sopt.kclean.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
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

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.sopt.kclean.Controller.ImageHandler;
import org.sopt.kclean.Controller.Post;
import org.sopt.kclean.Controller.PostString;
import org.sopt.kclean.Model.User;
import org.sopt.kclean.R;
import org.sopt.kclean.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateGroupActivity extends AppCompatActivity {

    //앨범에서 사진 가져오기
    public static final int PICK_BACKGROUND_FROM_ALBUM = 1;
    public static final int PICK_GROUP_FROM_ALBUM = 2;

    private  RequestBody photoBody;
    RequestBody img;
    private ImageButton create_group_complete_button;
    private ImageButton create_group_groupBackground_button;
    private EditText create_group_groupName_editTxt;
    private CircleImageView create_group_groupImage_circleView;
    private EditText create_group_groupDetail_editTxt;
    private EditText bank_name_editTxt;
    private EditText bank_accout_editTxt;
    private User user;
    private String backgroundUri = "";
    private String groupUri = "";

    private static final String[] IMAGE_PROJECTION = {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.Thumbnails.DATA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        Intent intent;
        intent = getIntent();
        user = intent.getParcelableExtra("user");

        // xml이랑 연결 우히히
        create_group_complete_button = (ImageButton) findViewById(R.id.create_group_complete_button);
        create_group_groupBackground_button = (ImageButton) findViewById(R.id.create_group_groupBackground_button);
        create_group_groupName_editTxt = (EditText) findViewById(R.id.create_group_groupName_editTxt);
        create_group_groupImage_circleView = (CircleImageView) findViewById(R.id.create_group_groupImage_circleView);
        create_group_groupDetail_editTxt = (EditText) findViewById(R.id.create_group_groupDetail_editTxt);
        bank_accout_editTxt = (EditText)findViewById(R.id.bank_account_editTxt);
        bank_name_editTxt = (EditText)findViewById(R.id.bank_name_editTxt);


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
                    checkVerify(PICK_BACKGROUND_FROM_ALBUM);
                }
                else
                {
                    startActivityForResult(intent, PICK_BACKGROUND_FROM_ALBUM);
                }

                Log.v("soominbb", "background || " + backgroundUri);
            }
        });

        // 동아리 사진 고르기
        create_group_groupImage_circleView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

//                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    checkVerify(PICK_GROUP_FROM_ALBUM);
                }
                else
                {
                    startActivityForResult(intent, PICK_GROUP_FROM_ALBUM);
                }

                Log.v("soominbb", "groupUri || " + groupUri);
            }
        });


        //완료 버튼
        create_group_complete_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.v("soomincc", "buttonClick1");

                String status = "ok"; //전송 결과 상태
                //서버랑 통신해서 동아리 정보 보냄 api 확정시 코드 추가
                // 통신
                CreateGroupTask createGroupTask = new CreateGroupTask();

                if (create_group_groupName_editTxt.getText().toString().equals("") || create_group_groupDetail_editTxt.getText().toString().equals("")) {
                    // 다이얼로그 바디
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CreateGroupActivity.this, R.style.MyAlertDialogStyle);
                    // 메세지
                    alert_confirm.setMessage("정보를 모두 입력해주세요.");
                    // 확인 버튼 리스너
                    alert_confirm.setPositiveButton("확인", null);
                    // 다이얼로그 생성
                    AlertDialog alert = alert_confirm.create();
                    // 아이콘
//                     alert.setIcon(R.drawable.ic_launcher);
                    // 다이얼로그 타이틀
                    // 다이얼로그 보기
                    alert.show();
                }
                else {
                    //Toast.makeText(CreateGroupActivity.this,"gUri : "+groupUri+" bUri"+backgroundUri,Toast.LENGTH_LONG).show();
                    createGroupTask.execute(create_group_groupName_editTxt.getText().toString(), groupUri, create_group_groupDetail_editTxt.getText().toString(), backgroundUri,bank_name_editTxt.getText().toString(),bank_accout_editTxt.getText().toString());
                }

                Log.v("soomincc", "buttonClick2");
            }
        });
    }


    //사진 관련해서 return 값 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String[] proj = {MediaStore.Images.Media.DATA};;
        Cursor cursor;
        int column_index;
        String imgPath;
        String imgName;
        Toast.makeText(this, "" + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PICK_BACKGROUND_FROM_ALBUM:
                    Toast.makeText(this, "" + data.getData(), Toast.LENGTH_SHORT).show();

//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    Cursor c = managedQuery(data.getData(), proj, null, null, null);
//                    int index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    c.moveToFirst();
                    cursor = managedQuery(data.getData(), proj, null, null, null);
                    column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    imgPath = cursor.getString(column_index);
                    imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
                    backgroundUri = imgPath;
                    ImageHandler.sendPicture(data.getData(),this, create_group_groupBackground_button); //갤러리에서 가져오기

                    break;
                case PICK_GROUP_FROM_ALBUM:
                    Toast.makeText(this, "" + data.getData(), Toast.LENGTH_SHORT).show();
                    cursor = managedQuery(data.getData(), proj, null, null, null);
                     column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    imgPath = cursor.getString(column_index);
                    imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
                    groupUri = imgPath;

                    ImageHandler.sendPicture(data.getData(), this, create_group_groupImage_circleView); // 갤러리에서 가져오기

                    break;
            }
        }
    }

    //권한 체크
    public void checkVerify(int requestCode) {
        if (
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // ...
            }
            else if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

            }
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_PICK);

            //   intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, requestCode);
        }
    }

    private class CreateGroupTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Post post = new Post("https://klean.apps.dev.clayon.io/api/club",  PostString.clubJson(strings[0], strings[1], strings[2], strings[3],strings[4],strings[5]),user.getToken(), "multipart/form-data");
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

                // 다이얼로그 바디
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CreateGroupActivity.this, R.style.MyAlertDialogStyle);
                // 메세지
                alert_confirm.setMessage("동아리 가입이 성공적으로 완료 되었습니다");
                // 확인 버튼 리스너
                alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
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
            }
            else
            {
                //응답 오류시  다시 로그인 하라고 하기
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
                if(alert != null && alert.isShowing())
                    alert.show();
            }

            super.onPostExecute(s);
        }
    }
}