package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by zhangfan on 2015/2/27.
 */
public class PictureMD5 {

    public InputStream getClassJpg() {
        return Class.class.getResourceAsStream("/aa.jpg");
    }

    public static void main(String[] args) throws Exception {
//        InputStream inputStream = new FileInputStream(new File("src/com/test/aa.jpg"));
//        PictureMD5 pictureMD5 = new PictureMD5();
//        InputStream inputStream = pictureMD5.getClassJpg();
        InputStream inputStream = Class.class.getResourceAsStream("/aa.jpg");

        byte[] bytes = new byte[1024];
        int len;

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        while ((len = inputStream.read(bytes)) > 0) {
            messageDigest.update(bytes, 0, len);
        }
        inputStream.close();
        byte[] bytes1 = messageDigest.digest();

        /*//2进制输出
        for (byte bt : bytes1) {
            for (int i = 0; i < 8; i++) {
                if(i > 3 && i % 4 == 0) System.out.print(" ");
                System.out.print(bt >>> i & 0x1);
            }
            System.out.print(" ");
        }*/

        //16进制输出
        char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        for (byte bt : bytes1) {
            System.out.print(chars[bt >>> 4 & 0xf]);
            System.out.print(chars[bt & 0xf]);
        }

        System.out.println("\n-------------------------------");
        System.out.println(bytes1.length);

        System.out.println(messageDigest.digest());
    }
}
