package com.sc.test;

import com.github.crab2died.ExcelUtils;
import com.sc.bean.TestCase;
import com.sc.http.HttpUtils;
import com.sc.util.CheckPointUtil;
import com.sc.util.MapUtil;
import com.sc.util.PatternUtil;
import com.sc.util.SaveParamsUtil;
import java.io.File;
import java.util.List;

public class ApiTest {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"apitest.xlsx";
        List<TestCase> list = ExcelUtils.getInstance().readExcel2Objects(path,TestCase.class);
        if(list!=null){
            for(TestCase testCase : list){
                PatternUtil.matcherParams(testCase);
                String responseJson =null;
                if("get".equals(testCase.getType())){
                    responseJson = HttpUtils.doGet(testCase.getUrl(), MapUtil.StringToMap(testCase.getHeader()));
                }else if("post".equals(testCase.getType())){
                    responseJson = HttpUtils.doPost(testCase.getUrl(), MapUtil.StringToMap(testCase.getParams(),"&"),MapUtil.StringToMap(testCase.getHeader()));
                }
               Boolean checkFlag =  CheckPointUtil.checkPoint(responseJson,testCase.getCheckP());
                System.out.println("checkFlag="+checkFlag);
                if(checkFlag){
                    SaveParamsUtil.saveParamsToMap(responseJson,testCase.getCorrelation());
                }
                System.out.println(responseJson);
            }
        }

    }
}
