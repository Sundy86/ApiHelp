package com.sc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getCurrentDate(){
        Date date =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD_hh_mm_ss");
        return  simpleDateFormat.format(date);
    }
}
