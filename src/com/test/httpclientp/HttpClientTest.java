package com.test.httpclientp;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

/**
 * Created by zhangfan on 2015/4/23.
 */
public class HttpClientTest {
    public static void main(String[] args) throws  Exception {

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.print(i + "\t");
                    System.out.println(Thread.currentThread().getName());
                    try {
                        sendRequest("202.96.128.143 ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/

        for (int i = 0; i < 100; i++) {
            System.out.print(i + "\t");
            sendRequest("202.96.199.132");
        }


    }

    public static void sendRequest(String ip) throws  Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = new URIBuilder().setScheme("http")
                .setHost("223.202.67.48")
                .setPath("/vote")
                .setParameter("captcha","")
                .setParameter("voteString","4|15|16|17|18|")
                .setParameter("keyString","jcjt2015")
                .setParameter("jsoncallback","jQuery18003499494264342973_"+System.currentTimeMillis())
                .setParameter("_", ""+System.currentTimeMillis())
                .build();


        System.out.println(uri.toString());
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }

            System.out.println(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
