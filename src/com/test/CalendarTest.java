package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhangfan on 2015/3/17.
 */
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.YEAR, 2015);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        System.out.println(calendar.get(Calendar.MONTH));

        System.out.println(simpleDateFormat.format(calendar.getTime()));

    }
}
