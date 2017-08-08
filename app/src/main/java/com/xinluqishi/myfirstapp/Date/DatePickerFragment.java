package com.xinluqishi.myfirstapp.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.xinluqishi.myfirstapp.R;
import com.xinluqishi.myfirstapp.utils.DateUtil;
import com.xinluqishi.myfirstapp.utils.ValidateUtil;
import com.xinluqishi.myfirstapp.utils.constant.DatePickerEnum;
import com.xinluqishi.myfirstapp.utils.constant.MyConstants;
import com.xinluqishi.myfirstapp.utils.ui.DialogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日历控件
 * Created by shikeyue on 17/4/25.
 */
public class DatePickerFragment extends DialogFragment {

    private boolean isEndDateComponent = false;   //判断是不是结束时间控件

    private String startDateValue;                //开始时间的值
    private String endDateValue;                  //结束时间的值

    private String currentDateStr;                //当前日期字符串
    private String limitEndDateStr;               //最大能选择的日期字符串

    private String typeMark;                      //标明日期控件的文字显示类型，是机票的还是酒店

    private TextView hintTextView;

    private String isSingleDateWidget;            //不是由日期范围触发的日期控件，只是一个日期控件选择点

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 获取日期控件的视图
        View view = inflater.inflate(R.layout.activity_date_picker, container, false);

        hintTextView = (TextView) view.findViewById(R.id.date_picker_tv_tip);

        // 初始化日期控件的形式，以及是否需要设置成可以选择范围
        final CalendarPickerView calendarPickerView = initCalendarPickerView(view);

        calendarPickerView.setTitleTypeface(Typeface.SANS_SERIF);

        calendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date selectDate) {
                List<Date> rangeDateList = calendarPickerView.getSelectedDates();         //选择的所有的日期
                int size = calendarPickerView.getSelectedDates().size();                  //一共几晚
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                hintTextView.setVisibility(View.GONE);

                boolean isEndDate = isEndDateComponent();
                if (!isEndDate) {
                    // 如果触发时间控件的是开始日期控件
                    if (size >= 2) {

                        DatePickerDecorator decorator = new DatePickerDecorator(getActivity(), getTypeMark());
                        List<CalendarCellDecorator> d = new ArrayList<>();
                        d.add(decorator);
                        calendarPickerView.setDecorators(d);

                        /*
                        * 选择的日期是一个区间
                         */
                        Date startDate = rangeDateList.get(0);
                        String startDateStr = format.format(startDate);
                        Date endDate = rangeDateList.get(size - 1);
                        String endDateStr = format.format(endDate);

                        Intent intent = setBundleValue(size, startDateStr, endDateStr);
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();

                    } else {

                        if (DatePickerEnum.Hotel.getValue().equals(getTypeMark())) {
                            hintTextView.setVisibility(View.VISIBLE);
                            hintTextView.setText(getString(R.string.hotel_end_date_hint_error));
                        } else if(DatePickerEnum.Air.getValue().equals(getTypeMark())){
                            hintTextView.setVisibility(View.VISIBLE);
                            hintTextView.setText(getString(R.string.air_end_date_hint_error));
                        }else{
                            hintTextView.setVisibility(View.VISIBLE);
                            hintTextView.setText(getString(R.string.travel_end_date_hint_error));
                        }

                    }
//                    else {
//                        /*
//                         * 选择的日期是一天
//                         */
//                        Date startDate = rangeDateList.get(0);
//                        String startDateStr = format.format(startDate);
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString(IConst.Bundle.SELECTDATE_START_DATE, startDateStr);
//                        Intent intent = new Intent();
//                        intent.putExtras(bundle);
//
//                        getActivity().setResult(Activity.RESULT_OK, intent);
//                        getActivity().finish();
//                    }
                } else {
                    // 如果触发时间控件的是结束日期控件
                    Date endDate = rangeDateList.get(0);
                    String endDateStr = format.format(endDate);

                    Date date = new Date();
                    String startDateValue = getStartDateValue();
                    int hotelTotalN = 0;

                    if(!ValidateUtil.isEmpty(startDateValue)) {

                        DatePickerEndDecorator decorator = new DatePickerEndDecorator(getActivity(), getTypeMark());
                        List<CalendarCellDecorator> endDecorator = new ArrayList<>();
                        endDecorator.add(decorator);
                        calendarPickerView.setDecorators(endDecorator);

                        // 存在开始时间的调用
                        if (DateUtil.compareDate(startDateValue, endDateStr) < 0) {
                            date = DateUtil.str2Date(startDateValue, "yyyy-MM-dd");
                            hotelTotalN = DateUtil.daysBetween(date, endDate);

                            Intent intent = setBundleValue(hotelTotalN, startDateValue, endDateStr);
                            getActivity().setResult(Activity.RESULT_OK, intent);
                            getActivity().finish();
                        } else {
                            String errMessage =
                                    getResources().getString(R.string.hotel_invalid_end_date_error);
                            DialogUtil.showToastCust(errMessage);
                        }
                    } else {
                        // 不存在开始时间的调用，即日期控件不是一个时间段的时候
                        Bundle bundle = new Bundle();
                        bundle.putString(MyConstants.DateConstants.SELECTDATE_END_DATE, endDateStr);
                        bundle.putInt(MyConstants.BaseConstants.WIN_RESULT, MyConstants.BaseConstants.WIN_SELECT_DATE);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);

                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }

            @NonNull
            protected Intent setBundleValue(int size, String startDateStr, String endDateStr) {
                Bundle bundle = new Bundle();
                bundle.putString(MyConstants.DateConstants.SELECTDATE_START_DATE, startDateStr);
                bundle.putString(MyConstants.DateConstants.SELECTDATE_END_DATE, endDateStr);
                bundle.putString(MyConstants.DateConstants.SELECTDATE_TOTAL_NIGHT, String.valueOf((size - 1) >= 0 ? (size - 1) : 0));
                bundle.putInt(MyConstants.BaseConstants.WIN_RESULT, MyConstants.BaseConstants.WIN_SELECT_DATE);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                return intent;
            }
        });

        // 无效日期选择错误信息监听
        calendarPickerView.setOnInvalidDateSelectedListener(new CalendarPickerView.OnInvalidDateSelectedListener() {
            @Override
            public void onInvalidDateSelected(Date date) {
                String errMessage =
                        getResources().getString(R.string.hotel_invalid_date, currentDateStr, limitEndDateStr);
                DialogUtil.showToastCust(errMessage);
            }
        });

        view.findViewById(R.id.date_picker_rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    /**
     * 初始化日期控件
     * @param view
     * @return
     */
    @NonNull
    protected CalendarPickerView initCalendarPickerView(View view) {

        if (DatePickerEnum.Hotel.getValue().equals(getTypeMark())) {
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.hotel_start_date_hint_error));
        } else if(DatePickerEnum.Air.getValue().equals(getTypeMark())){
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.air_start_date_hint_error));
        } else if(DatePickerEnum.Travel.getValue().equals(getTypeMark())){
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.travel_start_date_hint_error));
        }

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        final CalendarPickerView calendar = (CalendarPickerView) view.findViewById(R.id.calendar_view);
        Date today = new Date();

        currentDateStr = DateUtil.getCurrentDateForSDF();
        // 最大能选择的日期
        limitEndDateStr = DateUtil.getCalendarStr(nextYear);

        if (isEndDateComponent()) {
            if (ValidateUtil.isNotEmpty(getStartDateValue())) {
                initCalendarWithInitDate(nextYear, calendar, today, DateUtil.str2Date(getStartDateValue(), "yyyy-MM-dd"));
            } else {
                if (ValidateUtil.isNotEmpty(getEndDateValue())) {

                    if (ValidateUtil.isNotEmpty(getIsSingleDateWidget()) && MyConstants.DateConstants.DATE_SINGLE.equals(getIsSingleDateWidget())) {
                        //是单个日期控件点触发的日期控件
                        initCalendarWithInitDateSingle(nextYear, calendar, today, DateUtil.str2Date(getEndDateValue(), "yyyy-MM-dd"));
                    } else {
                        //是日期时间段的结束时间输入框触发的日期控件
                        initCalendarWithInitDate(nextYear, calendar, today, DateUtil.str2Date(getEndDateValue(), "yyyy-MM-dd"));
                    }

                } else {
                    initCalendarMode(nextYear, calendar, today, CalendarPickerView.SelectionMode.SINGLE);
                }
            }
        } else {
            if (ValidateUtil.isNotEmpty(getStartDateValue()) && ValidateUtil.isNotEmpty(getEndDateValue())) {
                setDateRangeToDatePicker(nextYear, calendar, today, CalendarPickerView.SelectionMode.RANGE);
            } else {
                initCalendarMode(nextYear, calendar, today, CalendarPickerView.SelectionMode.RANGE);
            }
        }

        return calendar;
    }

    private void initCalendarWithInitDate(Calendar nextYear, CalendarPickerView calendar, Date today, Date selectedDates) {
        calendar.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE)
                .withSelectedDate(selectedDates);

        if (DatePickerEnum.Hotel.getValue().equals(getTypeMark())) {
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.hotel_end_date_hint_error));
        } else if(DatePickerEnum.Air.getValue().equals(getTypeMark())){
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.air_end_date_hint_error));
        }else{
            hintTextView.setVisibility(View.VISIBLE);
            hintTextView.setText(getString(R.string.travel_end_date_hint_error));
        }

        DatePickerDecorator decorator = new DatePickerDecorator(getActivity(), getTypeMark());
        List<CalendarCellDecorator> d = new ArrayList<>();
        d.add(decorator);
        calendar.setDecorators(d);

    }

    /**
     * 单个日期控件触发点
     * @param nextYear
     * @param calendar
     * @param today
     * @param selectedDates
     */
    private void initCalendarWithInitDateSingle(Calendar nextYear, CalendarPickerView calendar, Date today, Date selectedDates) {
        calendar.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE)
                .withSelectedDate(selectedDates);

        hintTextView.setVisibility(View.GONE);

        DatePickerSingleDecorator decoratorSingle = new DatePickerSingleDecorator(getActivity());
        List<CalendarCellDecorator> dsingle = new ArrayList<>();
        dsingle.add(decoratorSingle);
        calendar.setDecorators(dsingle);

    }

    private void initCalendarMode(Calendar nextYear, CalendarPickerView calendar, Date today, CalendarPickerView.SelectionMode type) {
        calendar.init(today, nextYear.getTime()).inMode(type);
    }

    /**
     * 如果已经选择了日期，那么调用日期控件的时候进行设置
     * @param nextYear
     * @param calendar
     * @param today
     */
    private void setDateRangeToDatePicker(Calendar nextYear, CalendarPickerView calendar, Date today, CalendarPickerView.SelectionMode type) {
        List<Date> dates = new ArrayList<>();
        dates.add(DateUtil.str2Date(getStartDateValue(), "yyyy-MM-dd"));
        dates.add(DateUtil.str2Date(getEndDateValue(), "yyyy-MM-dd"));
        calendar.init(today, nextYear.getTime())
                .inMode(type)
                .withSelectedDates(dates);

        DatePickerDecorator decorator = new DatePickerDecorator(getActivity(), getTypeMark());
        List<CalendarCellDecorator> d = new ArrayList<>();
        d.add(decorator);
        calendar.setDecorators(d);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    /**
     * setter getter method
     * by shikeyue
     **/
    public String getStartDateValue() {
        return startDateValue;
    }

    public void setStartDateValue(String startDateValue) {
        this.startDateValue = startDateValue;
    }

    public String getEndDateValue() {
        return endDateValue;
    }

    public void setEndDateValue(String endDateValue) {
        this.endDateValue = endDateValue;
    }

    public boolean isEndDateComponent() {
        return isEndDateComponent;
    }

    public void setEndDateComponent(boolean endDateComponent) {
        isEndDateComponent = endDateComponent;
    }

    public String getTypeMark() {
        return typeMark;
    }

    public void setTypeMark(String typeMark) {
        this.typeMark = typeMark;
    }

    public String getIsSingleDateWidget() {
        return isSingleDateWidget;
    }

    public void setIsSingleDateWidget(String isSingleDateWidget) {
        this.isSingleDateWidget = isSingleDateWidget;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().finish();
    }
}
