package com.sc.util;

import com.sc.bean.TestCase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    private static Pattern patternRule = Pattern.compile("\\$\\{(.*?)\\}");//${id}
    public static void matcherParams(TestCase testCase){
        Matcher matcher = patternRule.matcher(testCase.getUrl());
        while (matcher.find()){
          //  System.out.println(matcher.group());
            String key = matcher.group(1);
            String value = SaveParamsUtil.get(key).toString();
            String url = testCase.getUrl().replace(matcher.group(),value);
            testCase.setUrl(url);
            System.out.println("newurl--->"+testCase.getUrl());
        }
    }

    public static String  matcherParams(String str){
        Matcher matcher = patternRule.matcher(str);
        String url = null;
        while (matcher.find()){
           // System.out.println(matcher.group());
            String key = matcher.group(1);
            String value = SaveParamsUtil.get(key).toString();
            url = str.replace(matcher.group(),value);
        }
        return  url;
    }
}
