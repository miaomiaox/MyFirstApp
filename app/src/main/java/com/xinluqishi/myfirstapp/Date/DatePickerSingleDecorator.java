package com.xinluqishi.myfirstapp.Date;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.xinluqishi.myfirstapp.R;

import java.util.Date;

/**
 * 修改日期控件背景效果
 * Created by shikeyue on 17/5/25.
 */


public class DatePickerSingleDecorator implements CalendarCellDecorator {

    private Context context;

    public DatePickerSingleDecorator(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void decorate(CalendarCellView cellView, Date date) {

        Drawable checkedDraw = context.getResources().getDrawable(R.mipmap.icon_single_blank,null);
        Drawable combineDraw = context.getResources().getDrawable(R.mipmap.icon_single_combine,null);
        Drawable todayDraw = context.getResources().getDrawable(R.mipmap.icon_today_img,null);
        Drawable blankDraw = context.getResources().getDrawable(R.mipmap.icon_blank_hb,null);

        if (cellView.isSelectable()) { //先通过是否可选方法来区分时间,如果可选则再判断是否已选
            if (cellView.isSelected()) {//再通过是否已选来设置日期的背景图片
                String json = String.valueOf(cellView.getTag());
                String tagLocation = json.substring(json.indexOf("rangeState") + 11, json.length() - 1);
                String isSelected = json.substring(json.indexOf("isSelected") + 11, json.indexOf("isSelected") + 15);
                //处理选中日期的样式
                if (("NONE".equals(tagLocation)) && ("true".equals(isSelected))) {
                    cellView.setBackground(checkedDraw);
                }

                if (cellView.isToday()) {
                    Log.d("DateDecorator5", String.valueOf(cellView.getTag()));
                    cellView.setBackground(combineDraw);
                }
            } else {
                if (cellView.isToday()) {
                    cellView.setBackground(todayDraw);
                } else {
                    cellView.setBackground(blankDraw);
                }
            }
        }else {//如果为不可选时间则直接设置日期背景
            String json = String.valueOf(cellView.getTag());
            String currentMonthBoolean = json.substring(json.indexOf("isCurrentMonth") + 15, json.indexOf("isCurrentMonth") + 20);
            if ("false".equals(currentMonthBoolean)) {
                cellView.getDayOfMonthTextView().setText("");
                cellView.setBackground(blankDraw);
            }
        }
    }
}
