package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayConvertList {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        ListToArray(list);

        Object [] str = new Object[]{"a","d","e","s","1"};
        ArrayToList(str);

    }


    public static void ArrayToList(Object[] obj){
        System.out.println("方法一-----------------------------------------");
        List<Object> list = new ArrayList<Object>();
        //String数组转List集合
        for(Object str : obj){
            list.add(str);
        }
        // 输出List集合
        for(Object li : list){
            System.out.println(li.toString());
        }

        System.out.println("方法二-----------------------------------------");

        // String数组转List集合
        List<Object> list1 = Arrays.asList(obj);
        // 输出List集合
        for(Object li : list1){
            System.out.println(li.toString());
        }
    }

    public  static void ListToArray(List<Object> list){
        System.out.println("方法一-----------------------------------------");
        Object[] strArray = new Object[list.size()];
        // List转换成数组
        for(int i=0;i<strArray.length;i++){
            strArray[i]=list.get(i);
        }

        // 输出List集合
        for(Object s : strArray){
            System.out.println(s.toString());
        }


        System.out.println("方法二-----------------------------------------");
        // List转换成数组
        Object[] str = list.toArray(new Object[list.size()]);
        // 输出List集合
        for(Object s:str){
            System.out.println(s.toString());
        }

    }
}
