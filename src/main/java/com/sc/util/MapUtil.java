package com.sc.util;

import java.util.HashMap;
import java.util.Map;
public class MapUtil {
    public static Map StringToMap(String str, String regx){

        Map<String,Object> map = null;
        if(str!=null) {
            String[] strs = str.trim().split(regx);
            map = new HashMap<String,Object>();
            for (String s : strs) {
                String[] ss = s.split("=");
                map.put(ss[0], ss[1]);
            }
        }
        return  map;
    }
    public static Map StringToMap(String str){
        return StringToMap(str,";");
    }
}
