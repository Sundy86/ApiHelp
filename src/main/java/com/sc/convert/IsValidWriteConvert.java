package com.sc.convert;

import com.github.crab2died.converter.WriteConvertible;

public class IsValidWriteConvert implements WriteConvertible {
    @Override
    public Object execWrite(Object o) {
        boolean flag =(Boolean)o;
        return flag==true?"有效":"无效";
    }
}
