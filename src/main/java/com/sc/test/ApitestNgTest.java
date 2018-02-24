package com.sc.test;

import com.github.crab2died.ExcelUtils;
import com.sc.bean.TestCase;
import com.sc.http.HttpClientException;
import com.sc.http.HttpUtils;
import com.sc.util.CheckPointUtil;
import com.sc.util.MapUtil;
import com.sc.util.PatternUtil;
import com.sc.util.SaveParamsUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApitestNgTest {
    @Test(dataProvider = "apiData",threadPoolSize = 3,invocationCount = 5)
    public void testAPI(TestCase testCase) throws HttpClientException {
        PatternUtil.matcherParams(testCase);
        String responseJson = null;
        if ("get".equals(testCase.getType())) {
            responseJson = HttpUtils.doGet(testCase.getUrl(), MapUtil.StringToMap(testCase.getHeader()));
        } else if ("post".equals(testCase.getType())) {
            responseJson = HttpUtils.doPost(testCase.getUrl(), MapUtil.StringToMap(testCase.getParams(), "&"), MapUtil.StringToMap(testCase.getHeader()));
        } else if ("postjson".equals(testCase.getType())) {
            responseJson = HttpUtils.doPostJson(testCase.getUrl(), testCase.getParams(), MapUtil.StringToMap(testCase.getHeader()));
        }
        Boolean checkFlag = CheckPointUtil.checkPoint(responseJson, testCase.getCheckP());
       // System.out.println("checkFlag=" + checkFlag);
        if (checkFlag) {
            SaveParamsUtil.saveParamsToMap(responseJson, testCase.getCorrelation());
         }
        Assert.assertTrue(checkFlag);
    }

    @DataProvider(name = "apiData")
    public Iterator<Object[]> providerData() throws Exception {
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "apitest.xlsx";
        List<Object[]> list = new ArrayList<Object[]>();
        List<TestCase> testCaseList = ExcelUtils.getInstance().readExcel2Objects(path, TestCase.class);
        for (TestCase testCase : testCaseList) {
            if (testCase.isValidFlag()) {
                list.add(new Object[]{testCase});
            }
        }
        return list.iterator();
    }

}
