package com.test;

import java.math.BigDecimal;

/**
 * Created by zhangfan on 2015/4/16.
 */
public class BigDecimalCompare {

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("63.22");
        BigDecimal bigDecimal2 = new BigDecimal("55.33");

        System.out.println(bigDecimal2.compareTo(bigDecimal1));
    }
}
