package com.work.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.sc.http.HttpClientException;
import com.sc.http.HttpUtils;

import java.math.BigDecimal;
import java.util.Map;

public class JsonUtil {
    static String randomStr = RandomUtil.randomStr();

    public static Object parseJsonString(){
        String jsonStr = null;
        try {
            jsonStr = HttpUtils.doGet("http://10.94.93.218/taskSubmit/1219_sample_v0.7.txt");
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
        Object jsonObj = JSON.parse(jsonStr);
       return  jsonObj;
}

    public static Object getJson(Object jsonObj, String jsonPath, String replaceStr) {
        jsonObj = UniqueParams(jsonObj);
        jsonObj =  replaceJson(jsonObj,jsonPath,replaceStr);
        if(isNumber(jsonPath)&&replaceStr.length()>0){
            BigDecimal num = new BigDecimal(replaceStr);
            jsonObj =  replaceJson(jsonObj,jsonPath,num);
        }
        return jsonObj;
    }

    public static void jsonChange(Object jsonObj, String key) {

        if (jsonObj instanceof JSONObject) {
            JSONObject object = (JSONObject) jsonObj;
            for (Map.Entry<String, Object> entry : object.entrySet()) {
                Object valObj = entry.getValue();
                jsonChange(valObj, key);
                String val = valObj.toString();
                String ke = entry.getKey();
                if (ke.equals(key) && val.indexOf(randomStr) == -1) {
                    val = entry.getValue() + randomStr;
                    entry.setValue(val);
                }
            }
        }

    }

    public static Object UniqueParams(Object jsonObj) {
        String[] keys = {"reportNo", "claimItemUniqueId", "claimUniqueId"};
        for (String key : keys) {
            jsonChange(jsonObj, key);
        }
        return  jsonObj;
    }

    public  static Object replaceJson(Object jsonObj,String jsonPath,Object val){
        JSONPath.set(jsonObj,jsonPath,val);
        return jsonObj;
    }

    public static boolean isNumber(String jsonPath){
        String [] jsonParams = {"dutyTypeRatio","totalEstimateAmount","totalRescueFeeAmount",
                "entireSalvage","purchasePrice","currentValue","unitPriceDiscount",
                "partQuantity","partFeeAfterDiscount","laborFeeAfterDiscount",
                "outerRepairFee","insuredAmount"};
        String[] nodes = jsonPath.split("\\.");
        if(nodes.length>1){
            String nodeName = nodes[nodes.length-1];
            for(String param : jsonParams){
                if(nodeName.equals(param)){
                    return  true;
                }
            }
        }else{
            return  false;
        }
        return false;
    }
}
