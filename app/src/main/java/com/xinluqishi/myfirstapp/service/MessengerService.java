package com.xinluqishi.myfirstapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xinluqishi.myfirstapp.utils.constant.MyConstants;

/**
 * Server side to handle the messenger
 * Created by shikeyue on 2017/7/30.
 */

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";


    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MyConstants.BaseConstants.MSG_FROM_CLIENT:
                    Log.i(TAG, "receive message from client:" + msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }
            super.handleMessage(msg);
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
