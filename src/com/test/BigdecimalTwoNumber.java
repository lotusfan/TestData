package com.test;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by zhangfan on 2015/3/16.
 */
public class BigdecimalTwoNumber {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("10.3365");
        BigDecimal bigDecimal1 = new BigDecimal("5");

        System.out.println(bigDecimal.divide(bigDecimal1, 2, BigDecimal.ROUND_DOWN).intValue() * 100);


        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(bigDecimal));



    }
}
