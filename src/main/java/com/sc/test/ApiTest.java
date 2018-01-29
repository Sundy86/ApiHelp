package com.sc.test;

import com.github.crab2died.ExcelUtils;
import com.sc.bean.TestCase;
import com.sc.email.EmailParams;
import com.sc.email.EmailUtils;
import com.sc.http.HttpUtils;
import com.sc.util.*;

import java.io.File;
import java.util.List;

public class ApiTest {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"apitest.xlsx";
        List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path,TestCase.class);
        if(list!=null){
            for(TestCase testCase : list){
                if(testCase.isValidFlag()){
                    PatternUtil.matcherParams(testCase);
                    String responseJson =null;
                    if("get".equals(testCase.getType())){
                        responseJson = HttpUtils.doGet(testCase.getUrl(), MapUtil.StringToMap(testCase.getHeader()));
                    }else if("post".equals(testCase.getType())){
                        responseJson = HttpUtils.doPost(testCase.getUrl(), MapUtil.StringToMap(testCase.getParams(),"&"),MapUtil.StringToMap(testCase.getHeader()));
                    }else  if("postjson".equals(testCase.getType())){
                        responseJson = HttpUtils.doPostJson(testCase.getUrl(), testCase.getParams(),MapUtil.StringToMap(testCase.getHeader()));
                    }
                    Boolean checkFlag =  CheckPointUtil.checkPoint(responseJson,testCase.getCheckP());
                    System.out.println("checkFlag="+checkFlag);
                    if(checkFlag){
                        SaveParamsUtil.saveParamsToMap(responseJson,testCase.getCorrelation());
                        testCase.setResult("测试通过");
                    }else{
                        testCase.setResult("测试失败");
                    }
                    System.out.println(responseJson);
                }else{
                    testCase.setResult("测试关闭");
                }
            }
        }
        String resultPath = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"result_API_"+ DateUtil.getCurrentDate()+".xlsx";
        ExcelUtils.getInstance().exportObjects2Excel( list, TestCase.class, resultPath);
        EmailUtils.sendEmailWithAttachment("api测试结果","具体测试结果，请查看附件，谢谢！", EmailParams.username,EmailParams.password,EmailParams.tousers,resultPath);
    }

}
