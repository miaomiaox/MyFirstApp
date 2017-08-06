package com.xinluqishi.myfirstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xinluqishi.myfirstapp.bean.UserManager;
import com.xinluqishi.myfirstapp.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_second)
public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
        Log.d(TAG, "staUserId:" + UserManager.staUserId);
    }

    @Event(R.id.smart_button_click)
    private void skipToAnotherButton(View view) {
        Log.d(TAG, "OnCreate");
        Log.d(TAG, "staUserId:" + UserManager.staUserId);
    }

}
