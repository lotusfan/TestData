package com.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by zhangfan on 15/12/12.
 */
public class RandomNum {


    public static void main(String[] args) {

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        System.out.println();


        BigDecimal bb = new BigDecimal(0);
        bb = bb.add(new BigDecimal(1));
        System.out.println(bb);
    }
}
