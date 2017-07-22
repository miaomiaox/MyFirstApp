package com.xinluqishi.myfirstapp;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * Created by shikeyue on 2017/7/22.
 */

public class BaseActivity extends AppCompatActivity {

    private Application application = null;

    private BaseActivity context = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getApplication();
        context = this;
        x.view().inject(this);
    }
}
