package com.test.httpclientp;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.test.rsa.Base64Utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangfan on 2015/6/3.
 */
public class HttpTimeOut {


    public static void main(String[] args) throws Exception {

        byte[] bytes =  Base64Utils.decode(new String(doGet()));

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D://aa.jpg"));

        fileOutputStream.write(bytes);
        fileOutputStream.close();



    }

    public static byte[] doGet() throws Exception {

        long startTime = System.currentTimeMillis();
        InputStream inputStream = null;
        ByteOutputStream byteOutputStream = null;
        try {
            URL localURL = new URL("http://manhua1025.61-147-113-113.cdndm5.com/1/567/6445/105_8173.jpg?cid=6445&key=d16412990490ae8563bafbd419808db9");
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

           /* httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setConnectTimeout(10 * 1000);

            httpURLConnection.setReadTimeout(5*1000);*/

            httpURLConnection.setRequestProperty("Referer","http://www.dm5.com/m6445-p7/");


            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            byteOutputStream = new ByteOutputStream();
            int i = 0;

            while ((i = inputStream.read()) > 0) {
                byteOutputStream.write(i);
            }


        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {


            if (byteOutputStream != null) {
                byteOutputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

            System.out.println(System.currentTimeMillis() - startTime);
        }


        return byteOutputStream.getBytes();
    }
}
