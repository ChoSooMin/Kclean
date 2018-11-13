package org.sopt.kclean.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.sopt.kclean.R;

/**
 * Created by choisunpil on 14/11/2018.
 */

public class DialogCustom {

    private static final int LAYOUT = R.layout.dialog_custom;
    private Context context;
    private String text;
    private Button button;
    private TextView textView;

    public DialogCustom(Context context) {
        this.context = context;
    }

//    public DialogCustom(Context context,String text) {
//        this.context = context;
//        this.text = text;
//
//
//    }

    public void callFunction(String text) {
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.dialog_custom);

        final TextView dialog_notice_text = (TextView) dlg.findViewById(R.id.dialog_notice_text);
        final TextView dialog_text = (TextView) dlg.findViewById(R.id.dialog_text);
        final Button notice_button = (Button) dlg.findViewById(R.id.notice_button);

        dialog_text.setText(text);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();


        notice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }

}
