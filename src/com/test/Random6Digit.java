package com.test;

import java.util.Random;

/**
 * Created by zhangfan on 2015/3/18.
 */
public class Random6Digit {
    public static int generatePin() throws Exception {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());

        int num = generator.nextInt(899999) + 100000;
        if (num < 100000 || num > 999999) {
            num = generator.nextInt(99999) + 99999;
            if (num < 100000 || num > 999999) {
                throw new Exception("Unable to generate PIN at this time..");
            }
        }
        return num;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(generatePin());


        Random random = new Random();
        System.out.println(random.nextInt(10));

    }
}
