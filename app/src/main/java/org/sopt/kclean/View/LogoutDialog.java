package org.sopt.kclean.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sopt.kclean.R;

public class LogoutDialog extends Dialog {
    private static final int LAYOUT = R.layout.dialog_custom;
    private Context context;
    private String text;
    private ImageButton button;
    private TextView textView;
    private RelativeLayout layout;

    SharedPreferences pref;

    public LogoutDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        button = (ImageButton) findViewById(R.id.notice_button);
        textView = (TextView) findViewById(R.id.dialog_text);
        layout = (RelativeLayout) findViewById(R.id.dialog_style);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        textView.setText("로그아웃 하시겠습니까?");

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pref = context.getSharedPreferences("user_info", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();//저장하려면 editor가 필요
                editor.clear();
                editor.commit();

                Activity activity = (Activity) context;
                activity.finish();
            }
        });
    }
}
