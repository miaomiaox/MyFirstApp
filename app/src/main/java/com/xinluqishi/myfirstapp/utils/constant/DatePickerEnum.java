package com.xinluqishi.myfirstapp.utils.constant;

/**
 * 日期控件类型
 * Created by huangzhe on 2017/7/13.
 */

public enum DatePickerEnum {
    Air("air"),Hotel("hotel"),Travel("travel");

    private String value;
    private DatePickerEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
