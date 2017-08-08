package com.xinluqishi.myfirstapp.utils.ui;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xinluqishi.myfirstapp.R;


/**
 * 窗口工具类,提供可重用的窗口
 */
public class DialogUtil {

    private static Toast mToast;//为了实现疯狂模式下toast延时消失的问题
    private static Toast mToastCust ;

    /**
     * 显示并自动关闭
     * @param act
     * @param msg
     */
    public static void showToastOnUIThread(final Activity act,final String msg) {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToastCust(act,msg);
            }
        });

    }

    /**
     * 此方法可以避免疯狂模式下点击按钮造成的长时间显示toast的问题
     * @param ctx
     * @param msg
     */
    public static void showToastCust(Context ctx, String msg) {
        if(mToast==null){
            mToast = new Toast(ctx);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_LONG);
            View toastRoot = LayoutInflater.from(ctx).inflate(R.layout.toast_my, null);
            mToast.setView(toastRoot);
        }
        TextView message = (TextView) mToast.getView().findViewById(R.id.tv_toast);
        message.setText(msg);
        mToast.show();
    }

    public static void showNoNet(Context ctx) {
        showToastCust(ctx, "网络不可用，请检查网络！");
    }


    //va 引用了 ContextUtil
    /**
     *
     * @param msg
     */
    public static void showToastCust(String msg){
        showToastCust(ContextUtil.getAppContext(),msg);
    }
}