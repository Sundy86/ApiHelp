package com.work.test;

import com.sc.http.HttpClientException;
import com.work.util.JsonUtil;

public class RuleJsonTest {
    public static void main(String[] args) throws HttpClientException {
        Object jsonObj = JsonUtil.parseJsonString();
        Object json1 = JsonUtil.getJson(jsonObj, "$.carInfo.bulletinCode", "AAAAAAAA");
        Object json2 = JsonUtil.getJson(jsonObj, "$.taskWorkflow.totalEstimateAmount", "3010.88");
        System.out.println(json2.toString());


    }

}
