package com.xinluqishi.myfirstapp.utils.constant;

/**
 * Created by shikeyue on 2017/7/30.
 */
public interface MyConstants {

    interface BaseConstants {
        int MSG_FROM_CLIENT = 1;
    }


    interface DateConstants {
        String SELECTDATE_START_DATE = "selectdateStartDate";       //酒店入住开始时间
        String SELECTDATE_END_DATE = "selectdateEndDate";           //酒店入住离店时间
        String SELECTDATE_TOTAL_NIGHT = "selectdateTotalNight";     //酒店入住几晚
        String IS_END_DATE_INPUT = "isEndDateInput";                //是否在结束时间输入框触发时间控件
        String IS_SINGLE_DATE_WIDGET = "isSingleDateWidget";        //仅有一个控件，调用日期控件的出发点不是一个日期范围
        String TYPE_MARK = "typeMark";                  //标明日期控件的文字显示是关于哪种类型的
    }


}
