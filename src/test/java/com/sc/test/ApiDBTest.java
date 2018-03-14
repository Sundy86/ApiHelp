package com.sc.test;

import com.sc.bean.TestCase;
import com.sc.dao.TestCaseDao;
import com.sc.http.HttpClientException;
import com.sc.http.HttpUtils;
import com.sc.util.CheckPointUtil;
import com.sc.util.MapUtil;
import com.sc.util.PatternUtil;
import com.sc.util.SaveParamsUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class ApiDBTest {
    @Test
    public void test() throws SQLException, HttpClientException {
       List<TestCase> list = TestCaseDao.selectAll();
       for(TestCase testCase:list){
           if(testCase.isValidFlag()) {
               PatternUtil.matcherParams(testCase);
               String responseJson = null;
               if ("get".equals(testCase.getType())) {
                   responseJson = HttpUtils.doGet(testCase.getUrl(), MapUtil.StringToMap(testCase.getHeader()));
               } else if ("post".equals(testCase.getType())) {
                   responseJson = HttpUtils.doPost(testCase.getUrl(), MapUtil.StringToMap(testCase.getParams(), "&"), MapUtil.StringToMap(testCase.getHeader()));
               } else if ("postjson".equals(testCase.getType())) {
                   responseJson = HttpUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtil.StringToMap(testCase.getHeader()));
               }

               Boolean  checkFlag= CheckPointUtil.checkPoint(responseJson, testCase.getCheckP());

               if (checkFlag) {
                   SaveParamsUtil.saveParamsToMap(responseJson, testCase.getCorrelation());
               }
               Assert.assertTrue(checkFlag);

               System.out.println("checkFlag=" + checkFlag);

           }
       }
    }
}
