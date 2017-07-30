package com.xinluqishi.myfirstapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by shikeyue on 2017/7/29.
 */

public class BookService extends Service {

    public BookService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
