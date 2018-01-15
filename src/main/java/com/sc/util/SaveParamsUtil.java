package com.sc.util;

import com.jayway.jsonpath.JsonPath;
import java.util.HashMap;
import java.util.Map;

public class SaveParamsUtil {
    private  static  Map<String,Object> paramsMap = new HashMap<String,Object>();
    public static void saveParamsToMap(String json,String params){
        Map<String,Object> map = MapUtil.StringToMap(params);
        if(map!=null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue().toString();
                Object obj = JsonPath.read(json, val);
                paramsMap.put(key, obj.toString());
            }
        }
    }

    public static Object get(String key){
        return  paramsMap.get(key);
    }
}
