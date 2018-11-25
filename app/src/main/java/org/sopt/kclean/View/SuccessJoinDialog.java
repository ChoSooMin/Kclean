package org.sopt.kclean.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sopt.kclean.R;

public class SuccessJoinDialog extends Dialog {
    private static final int LAYOUT = R.layout.dialog_custom;
    private Context context;
    private String text;
    private ImageButton button;
    private TextView textView;
    private RelativeLayout layout;

    public SuccessJoinDialog(@NonNull Context context) {
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

        textView.setText("회원가입이 완료되었습니다.");

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);

                context.startActivity(intent);
            }
        });
    }
}
