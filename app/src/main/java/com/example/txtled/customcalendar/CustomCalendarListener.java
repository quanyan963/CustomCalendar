package com.example.txtled.customcalendar;

import java.util.Date;

/**
 * Created by KomoriWu on 2017/12/20.
 */

public interface CustomCalendarListener {
    void onItemPress(Date date);
    void onItemLongPress(Date date);
}
