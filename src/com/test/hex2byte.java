package com.test;

/**
 * Created by zhangfan on 16/1/13.
 */
public class hex2byte {
    public static void main(String[] args) {
        String str = "我们";
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            System.out.println(b);
            System.out.println(Integer.toHexString(b&(0xFF)));
        }
        System.out.println(Integer.toHexString(18));
    }
}
