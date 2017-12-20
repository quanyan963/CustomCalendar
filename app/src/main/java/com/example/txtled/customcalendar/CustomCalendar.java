package com.example.txtled.customcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by KomoriWu on 2017/12/19.
 */

public class CustomCalendar extends LinearLayout {
    @BindView(R.id.img_Prev)
    ImageView imgPrev;
    @BindView(R.id.img_Next)
    ImageView imgNext;
    @BindView(R.id.tv_Date)
    TextView tvDate;
    @BindView(R.id.rl_calendar_head)
    RelativeLayout rlCalendarHead;
    @BindView(R.id.rv_day)
    GridView rvDay;

    private Calendar mCalendar = Calendar.getInstance();
    private String disPlayFormat;
    public CustomCalendarListener mListener;

    public CustomCalendar(Context context) {
        super(context);
        initControl(context, null, 0);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs, 0);
    }

    public CustomCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs, defStyleAttr);
    }

    private void initControl(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.custom_calendar, this);
        ButterKnife.bind(this);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.CustomCalendar);
        disPlayFormat = typedArray.getString(R.styleable.CustomCalendar_dateFormat);
        if (disPlayFormat == null){
            disPlayFormat = "MMM yyyy";
        }
        renderCalendar();
        typedArray.recycle();
    }

    @OnClick({R.id.img_Prev, R.id.img_Next, R.id.tv_Date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_Prev:
                mCalendar.add(Calendar.MONTH, -1);
                renderCalendar();
                break;
            case R.id.img_Next:
                mCalendar.add(Calendar.MONTH, 1);
                renderCalendar();
                break;
            case R.id.tv_Date:
                break;
        }
    }

    /**
     * 绘制日历
     */
    private void renderCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat(disPlayFormat);
        //SimpleDateFormat fromat = new SimpleDateFormat("dd");
        tvDate.setText(sdf.format(mCalendar.getTime()));

        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) mCalendar.clone();

        //calendar.set(Calendar.DAY_OF_MONTH, 0);
        //String lastDay = fromat.format(calendar.getTime());
        //将月份置于当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //当月天数
        int dayInMonth = calendar.getActualMaximum(Calendar.DATE);
        //当月第一周上个月占了几天  -1 是因为如果返回是周一 则为1，减一就代表上个月占了0天
        int prevDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //将绘制的当前日期的第一天绘制到相应星期的位置
        calendar.add(Calendar.DAY_OF_MONTH, -prevDay);

        int countDay = prevDay + dayInMonth + (7 - ((prevDay + dayInMonth) % 7));
        //循环赋值将所有天数赋值到cells中
        while (cells.size() < countDay) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        rvDay.setAdapter(new CalendarAdapter(getContext(), R.layout.calendar_view, cells));
//        rvDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mListener != null){
//                    mListener.onItemPress((Date) parent.getItemAtPosition(position));
//                }
//            }
//        });
    }
}
