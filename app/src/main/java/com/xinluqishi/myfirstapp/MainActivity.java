package com.xinluqishi.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xinluqishi.myfirstapp.bean.UserManager;
import com.xinluqishi.myfirstapp.ui.SubThreadActivity;
import com.xinluqishi.myfirstapp.ui.ipcClient.MessengerActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity");
//        setContentView(R.layout.activity_main);

        Button mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        Button webViewButton = (Button) findViewById(R.id.web_view_button);
        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

//        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:12345678"));
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        startActivity(callIntent);

        UserManager.staUserId = 2;
        Log.d(TAG, "staUserId:" + UserManager.staUserId);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent, time=" + intent.getLongExtra("time", 0));
    }

    @Event(R.id.test_single_task)
    private void testSingleTaskButton(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("time", System.currentTimeMillis());
        startActivity(intent);
    }

    @Event(R.id.to_second)
    private void toSecondButton(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    @Event(R.id.to_messenger_service)
    private void toMessengerService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MessengerActivity.class);
        startActivity(intent);
    }

    @Event(R.id.to_sub_thread_activity)
    private void toSubThreadButton(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SubThreadActivity.class);
        startActivity(intent);
    }

}
