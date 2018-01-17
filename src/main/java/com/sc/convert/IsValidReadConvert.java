package com.sc.convert;

import com.github.crab2died.converter.ReadConvertible;

public class IsValidReadConvert implements ReadConvertible {
    @Override
    public Object execRead(String s) {
        if("有效".equals(s)){
            return true;
        }else {
            return false;
        }
    }
}
