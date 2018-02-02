package com.demo;

import java.util.HashMap;
import java.util.Map;

public class CharCountTest {
    public static void main(String[] args) {
        String str ="dkf1ksdf1sdfdllldfl29sdffss";
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(char c:chars){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            System.out.println("字符:"+entry.getKey()+"-出现的次数："+entry.getValue());
        }
    }
}
