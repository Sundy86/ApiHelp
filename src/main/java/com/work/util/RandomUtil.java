package com.work.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtil {
    public static String randomStr(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMDDHHmmss");
        String nowDate = simpleDateFormat.format(date);
        int random = (int)(Math.random()*10000+1);
        String randStr = nowDate+random;
        return  randStr;
    }
}
