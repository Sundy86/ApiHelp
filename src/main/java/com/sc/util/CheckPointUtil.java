package com.sc.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.jayway.jsonpath.JsonPath;
import java.util.HashMap;
import java.util.Map;

public class CheckPointUtil {
    public static Boolean checkPoint(String json,String checkParam){
        if(checkParam!=null && !"".equals(checkParam) && !"null".equals(checkParam)){
            Map<String,Object> map = new HashMap<String,Object>();
            String[] cParams =  checkParam.split(";");
            for(String params:cParams){
                String[] pars = params.split("=|>|<|>=|<=|==");
                checkParam = checkParam.replace(pars[0],"data");
                Object obj = JsonPath.read(json,pars[0]);
                if(obj instanceof  String){
                    checkParam =  checkParam.replace(pars[1],StrToAviatorString(pars[1]));
                    checkParam = checkParam.replace("=","==");
                }
                map.put("data",obj);
                Boolean bool = (Boolean) AviatorEvaluator.execute(checkParam,map);
                return  bool;
            }
        }
        return true;
    }
    public static String StrToAviatorString(String str){
        return  "'"+str+"'";
    }
}
