package com.sc.util;

import com.jayway.jsonpath.JsonPath;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveParamsUtil {
    private  static  Map<String,Object> paramsMap = new HashMap<String,Object>();
    public static void saveParamsToMap(String json,String params){
        Map<String,Object> map = MapUtil.StringToMap(params);
        if(map!=null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue().toString();
                if(key.endsWith("*")){
                   List<Object> list =  JsonPath.read(json,val);
                   String nodekey = key.split("_")[0];
                        for(int i=0;i<list.size();i++){
                           String k = nodekey + "_g" + (i+1);
                           String v = list.get(i).toString();
                           paramsMap.put(k, v);
                           System.out.println(k+"-------"+v);
                        }

                }else{
                    Object obj = JsonPath.read(json, val);
                    paramsMap.put(key, obj.toString());
                }
            }
        }
    }

    public static Object get(String key){
        return  paramsMap.get(key);
    }
}
