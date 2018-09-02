package com.winterchen.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by hanjd on 2017/7/31.
 */
public class DateUtil {
//    public static long getNextDayMillis(){
//        Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.DATE,1);
//        cal.set(Calendar.HOUR, 4);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        return  cal.getTimeInMillis() - System.currentTimeMillis();
//    }
    public static int getDayOfMonth(){
        return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    }


//    public static void main(String [] args){
//        long now = System.currentTimeMillis();
//        System.out.println(now);
//        System.out.println(getNextDayMillis());
//        System.out.println(getDayOfMonth());
//    }
}
