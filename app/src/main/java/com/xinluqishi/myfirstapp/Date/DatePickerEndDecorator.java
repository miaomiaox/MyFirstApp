package com.xinluqishi.myfirstapp.Date;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.xinluqishi.myfirstapp.R;
import com.xinluqishi.myfirstapp.utils.constant.DatePickerEnum;

import java.util.Date;

/**
 * 修改日期控件背景效果
 * Created by shikeyue on 17/5/25.
 */


public class DatePickerEndDecorator implements CalendarCellDecorator {

    private Context context;

    private String typeMark;

    public DatePickerEndDecorator(Context context, String typeMark) {
        this.context = context;
        this.typeMark = typeMark;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void decorate(CalendarCellView cellView, Date date) {

        Drawable endDraw = context.getResources().getDrawable(R.mipmap.icon_back_img,null);
        Drawable combineDraw = context.getResources().getDrawable(R.mipmap.icon_air_combine,null);
        Drawable todayDraw = context.getResources().getDrawable(R.mipmap.icon_today_img,null);
        Drawable blankDraw = context.getResources().getDrawable(R.mipmap.icon_blank_hb,null);

        if (DatePickerEnum.Hotel.getValue().equals(typeMark)) {
            //酒店
            endDraw = context.getResources().getDrawable(R.mipmap.icon_hotel_back,null);
            combineDraw = context.getResources().getDrawable(R.mipmap.icon_hotel_combine,null);
        }else if(DatePickerEnum.Air.getValue().equals(typeMark)){
            endDraw = context.getResources().getDrawable(R.mipmap.icon_back_img,null);
            combineDraw = context.getResources().getDrawable(R.mipmap.icon_air_combine,null);
        }else if(DatePickerEnum.Travel.getValue().equals(typeMark)){
            //提供旅游的图片
            endDraw = context.getResources().getDrawable(R.mipmap.icon_date_latest,null);
            combineDraw = context.getResources().getDrawable(R.mipmap.icon_date_combine,null);
        }

        if (cellView.isSelectable()) { //先通过是否可选方法来区分时间,如果可选则再判断是否已选
            if (cellView.isSelected()) {//再通过是否已选来设置日期的背景图片
                String json = String.valueOf(cellView.getTag());
                String tagLocation = json.substring(json.indexOf("rangeState") + 11, json.length() - 1);
                //处理选中日期的样式
                if ("NONE".equals(tagLocation)){
                    cellView.setBackground(endDraw);
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
