package com.test.cardata;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by zhangfan on 16/1/21.
 */
public class SuiYiBeiData {

    public static String[] columnName = {"仓库编码",
            "所在地",
            "零件编号",
            "属性",
            "易派价",
            "库存数量",
            "购买数量"};
    public static Queue<String> queueData = new ArrayDeque<>(1024);

    public static String cookies = "";
    public static String token = "";

    public static String loginname = "13426298429";
    public static String password = "hjx123";


    public static void main(String[] args) {

        try {

            login();
            MultiThreadDivide.divideParts(2011, 10);

            Thread.sleep(5000);
            MultiThreadWrite.writeExcel(10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void login() throws Exception {

        //http://www.qp51.com.cn/auth/login  mobile=13426298429&password=hjx123
        String referer = "http://www.qp51.com.cn/auth/login";

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.qp51.com.cn")
                .setPath("/auth/login")
                .setParameter("mobile", loginname)
                .setParameter("password", password)
                .build();
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        setRequestHeader(httpPost, referer);


        getResponse(httpPost, true);
        setToken();

    }

    public static void setToken() throws Exception{

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.qp51.com.cn")
                .setPath("/product")
                .build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Cookie", cookies);
        String data = getResponse(httpGet, false);

        Document document = Jsoup.parse(data);
        Elements tokenel = document.getElementsByAttributeValue("name", "_token");
        token = tokenel.get(0).val();
    }


    public static String sendRequest(int i) throws Exception {


        String referer = "http://www.qp51.com.cn/product?productType=1&keyword=5&_token=" + token + "&page=1";

        URI uri = new URIBuilder().setScheme("http")
                .setHost("www.qp51.com.cn")
                .setPath("/product")
                .setParameter("productType", "1")
                .setParameter("keyword", "5")
                .setParameter("_token", token)
                .setParameter("page", String.valueOf(i))
                .build();
        System.out.println(uri.toString());
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Cookie", cookies);
        setRequestHeader(httpGet, referer);
        return getResponse(httpGet, false);

    }

    public static void setRequestHeader(HttpRequestBase request, String referer) throws Exception {
        request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:43.0) Gecko/20100101 Firefox/43.0");
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Encoding", "gzip, deflate");
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        request.setHeader("Connection", "keep-alive");
        request.setHeader("Referer", referer);
    }

    public static String getResponse(HttpRequestBase request, boolean setCT) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        response = httpClient.execute(request);

        /*HeaderIterator headerIterator = response.headerIterator();
        while (headerIterator.hasNext()) {
            System.out.println(headerIterator.next());
        }*/

        if (setCT) {
            cookies = response.getFirstHeader("Set-Cookie").toString();
            cookies = cookies.substring(cookies.indexOf("laravel_session"), cookies.indexOf(";"));
        }


        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str);
        }


        return stringBuffer.toString();

    }

    public static void parseSuiYiBeiData(String data, WritableSheet sheet, MultiThreadWrite multiThreadWrite) throws Exception {

        Document document = Jsoup.parse(data);

        Elements content = document.select("div.content");

        Elements titles = content.first().select("div.spare-title");


        for (int j = 0; j < titles.size(); j++) {

            System.out.println(titles.text());
            Label label = new Label(0, (multiThreadWrite.row)++, titles.get(j).text());
            sheet.addCell(label);

            Element table = titles.get(j).nextElementSibling();
            Elements trs = table.select("tr");
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");

                for (int k = 0; k < columnName.length; k++) {
                    Label tdt = new Label(k, multiThreadWrite.row, tds.get(k).text());
                    sheet.addCell(tdt);
                }
                (multiThreadWrite.row)++;
            }

        }

    }

}
