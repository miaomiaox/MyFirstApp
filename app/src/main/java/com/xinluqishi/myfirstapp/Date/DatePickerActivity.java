package com.xinluqishi.myfirstapp.Date;

import android.os.Bundle;

import com.xinluqishi.myfirstapp.ui.base.BaseActivity;
import com.xinluqishi.myfirstapp.utils.ValidateUtil;
import com.xinluqishi.myfirstapp.utils.constant.DatePickerEnum;
import com.xinluqishi.myfirstapp.utils.constant.MyConstants;

/**
 * 日历控件
 * Created by shikeyue on 17/4/25.
 */
public class DatePickerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // HotelQueryActivity to DatePickerActivity
            boolean isEndDateInput = bundle.getBoolean(MyConstants.DateConstants.IS_END_DATE_INPUT);
            String startDateStr = bundle.getString(MyConstants.DateConstants.SELECTDATE_START_DATE);
            String endDateStr = bundle.getString(MyConstants.DateConstants.SELECTDATE_END_DATE);

            String isSingleDateWidget = "";
            if (ValidateUtil.isNotEmpty(bundle.getString(MyConstants.DateConstants.IS_SINGLE_DATE_WIDGET))) {
                isSingleDateWidget = bundle.getString(MyConstants.DateConstants.IS_SINGLE_DATE_WIDGET);
            }

            String typeMark = "";//类型
            if (ValidateUtil.isNotEmpty(bundle.getString(MyConstants.DateConstants.TYPE_MARK))) {
                typeMark = bundle.getString(MyConstants.DateConstants.TYPE_MARK);
            } else {
                typeMark = DatePickerEnum.Air.getValue();
            }

            if (!isEndDateInput) {
                // 不是由结束时间输入框触发的日期控件
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setTypeMark(typeMark);
                datePickerFragment.setEndDateComponent(false);
                if (ValidateUtil.isNotEmpty(startDateStr)) {
                    datePickerFragment.setStartDateValue(startDateStr);
                    if (ValidateUtil.isNotEmpty(endDateStr)) {
                        datePickerFragment.setEndDateValue(endDateStr);
                    }
                }
                datePickerFragment.show(getSupportFragmentManager(), "DatePickerFragment");
            } else {
                // 是由结束时间输入框触发的日期控件
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setTypeMark(typeMark);
                datePickerFragment.setEndDateComponent(true);
                datePickerFragment.setIsSingleDateWidget(isSingleDateWidget);
                if (ValidateUtil.isNotEmpty(startDateStr)) {
                    datePickerFragment.setStartDateValue(startDateStr);
                } else {
                    if (ValidateUtil.isNotEmpty(endDateStr)) {
                        datePickerFragment.setEndDateValue(endDateStr);
                    }
                }
                datePickerFragment.show(getSupportFragmentManager(), "DatePickerFragment");
            }
        }
    }

}
