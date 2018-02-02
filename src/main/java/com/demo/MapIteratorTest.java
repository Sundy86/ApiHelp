package com.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapIteratorTest {
    public static void main(String[] args) {
        Map<Object,Object> map = new HashMap<Object,Object>();
        for(int i=1;i<=20;i++){
            map.put(i,"mapValue"+i);
        }
        getMapvalue1(map);
        getMapValue2(map);
        getMapValue3(map);
        getMapValue4(map);
    }

    public static void getMapvalue1(Map<Object,Object> map){
        System.out.println("Map 遍历方法一:");
        for(Object o : map.keySet()){
            System.out.println("key:"+o.toString()+"  value:"+map.get(o).toString());
        }
    }

    public static void getMapValue2(Map<Object,Object> map){
        System.out.println("Map 遍历方法二:");
        for(Map.Entry<Object,Object> entry : map.entrySet()){
            System.out.println("key:"+entry.getKey().toString()+"  value:"+entry.getValue().toString());
        }
    }


    public static void getMapValue3(Map<Object,Object> map){
        System.out.println("Map 遍历方法三:");
        for(Object o : map.values()){
            System.out.println("value:"+o.toString());
        }
    }

    public static void getMapValue4(Map<Object,Object> map){
        System.out.println("Map 遍历方法四:");
        Iterator<Map.Entry<Object, Object>> iterator =map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Object,Object> entry = iterator.next();
            System.out.println("key:"+entry.getKey().toString()+"  value:"+entry.getValue().toString());
        }
    }
}
