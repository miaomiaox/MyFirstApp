package com.xinluqishi.myfirstapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinluqishi.myfirstapp.ui.base.BaseActivity;
import com.xinluqishi.myfirstapp.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_sub_thread)
public class SubThreadActivity extends BaseActivity {

    @ViewInject(R.id.change_text_view)
    private TextView changedTextView;

    @ViewInject(R.id.change_text_button)
    private Button changeTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.change_text_button:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                changedTextView.setText("Nice to meet you");
                            }
                        }).start();
                        break;
                    default:
                        break;

                }
            }
        });

    }

}
