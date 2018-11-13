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

public class DialogCustom extends Dialog {
    private static final int LAYOUT = R.layout.dialog_custom;
    private Context context;
    private String text;
    private Button button;
    private TextView textView;

    public DialogCustom(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public DialogCustom(@NonNull Context context,String text)
    {
        super(context);
        this.context = context;
        this.text = text;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        button = (Button) findViewById(R.id.noti_button);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }

}
