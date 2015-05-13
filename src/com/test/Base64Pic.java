package com.test;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.test.rsa.Base64Utils;
import it.sauronsoftware.base64.Base64;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Calendar;

/**
 * Created by zhangfan on 2015/3/25.
 */
public class Base64Pic {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("D://1.jpg"));

        ByteOutputStream byteOutputStream = new ByteOutputStream();
        int i = 0;
        while ((i = fileInputStream.read()) > 0) {
            byteOutputStream.write(i);
        }

        String str = Base64Utils.encode(byteOutputStream.getBytes());
        System.out.println(str);
        String ss = "AA";
        System.out.println(ss.getBytes().length);
        System.out.println(Base64.encode(ss));

    }
}
