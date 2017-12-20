package com.example.txtled.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KomoriWu on 2017/12/19.
 */

public class CalendarAdapter extends ArrayAdapter<Date> {
    private LayoutInflater mLayoutInflater;
    private CalendarTextView mDay;
    private ArrayList<Date> days;
    private int newPosition = -1,oldPosition = -1;

    public CalendarAdapter(@NonNull Context context, int resource, ArrayList<Date> days) {
        super(context, resource, days);
        this.days = days;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date date = getItem(position);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.calendar_view, parent, false);
        }
        //每一天
        int day = date.getDate();
        mDay = (CalendarTextView) convertView.findViewById(R.id.tv_day);
        if (position == oldPosition){
            mDay.isTouched = false;
            mDay.invalidate();
        }
        if (position == newPosition){
            mDay.isTouched = true;
            mDay.invalidate();
        }
        Date now = new Date();
        if (now.getDate() == date.getDate() && now.getMonth() == date.getMonth()
                && now.getYear() == date.getYear()) {
            mDay.setTextColor(Color.WHITE);
            mDay.isToday = true;
            mDay.invalidate();
        }
        mDay.setText(String.valueOf(day));
        mDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPosition = newPosition;
                newPosition = position;
                notifyDataSetChanged();
            }
        });


        if (position < 6) {
            if (day > 20) {
                mDay.setTextColor(Color.GRAY);
            }
        }
        if (position > days.size() - 7) {
            if (day < 20) {
                mDay.setTextColor(Color.GRAY);
            }
        }
        if (position % 7 == 6) {
            mDay.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        } else if (position % 7 == 0) {
            mDay.setTextColor(Color.RED);
        }
        return convertView;
    }
}
