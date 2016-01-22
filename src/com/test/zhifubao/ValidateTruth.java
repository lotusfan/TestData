package com.test.zhifubao;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhangfan on 16/1/12.
 */
public class ValidateTruth {
    public static void main(String[] args) {
        sendRequest();
    }

    public static void sendRequest(){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            URI uri = new URIBuilder().setScheme("https")
                    .setHost("mapi.alipay.com")
                    .setPath("/gateway.do")
                    .setParameter("service", "notify_verify")
                    .setParameter("notify_id", "c4598fef27bca62bdbf8a317fd01839m3")
                    .setParameter("partner", "2088121343239530")
                    .build();


            System.out.println(uri.toString());
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(""));
            CloseableHttpResponse response = null;

            response = httpClient.execute(httpPost);

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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }


    }
}
