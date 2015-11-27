package com.test.httpclientp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangfan on 2015/6/5.
 */
public class JsoupHtmlParser {
    public static void main(String[] args) throws  Exception{

        Document doc = Jsoup.connect("http://www.17500.cn/ssq/all2003.php").get();
//        Document doc = Jsoup.connect("http://www.17500.cn/ssq/all.php").get();
        Elements newsHeadlines = doc.select("a");

        Pattern pattern = Pattern.compile("\\((\\d{7})\\)");
        String urltail = null;
        StringBuffer stringBuffer = new StringBuffer();


        for (Element element : newsHeadlines) {
            String str = element.text();
            if (str.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    urltail = matcher.group(1);
                }
                System.out.println(urltail);
                Document document = Jsoup.connect("http://www.17500.cn/ssq/details.php?issue=" + urltail).get();

                Elements elementsRed = document.select("font[color=red]");
                for (Element red : elementsRed) {
                    Matcher matcherRed = Pattern.compile("\\d{2}").matcher(red.text());
                    if (matcherRed.matches()) {
                        stringBuffer.append("red[" + red.text() + "]");
                    }
                }

                Elements elementsBlu = document.select("font[color=blue]");
                for (Element blu : elementsBlu) {
                    stringBuffer.append("blu[" + blu.text() + "]\n");
                }
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D://doubleball2003.txt"));
        fileOutputStream.write(stringBuffer.toString().getBytes());
        fileOutputStream.close();




    }
}
