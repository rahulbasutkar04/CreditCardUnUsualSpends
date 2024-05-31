package com.amaap.creditcardunusualspends.util;

import java.util.Calendar;
import java.util.Date;

public class DateBuilder {
    public Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
