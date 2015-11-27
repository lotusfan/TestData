package com.test;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by zhangfan on 2015/10/16.
 */


public class HtmlEntity {

    /**
     * 替换HTMl里面的字符 e.g.: < > " å И 水
     *
     * @author ivan.yan
     */
    public static void main(String[] args) throws Exception {
        String s = "中文转字符的HTML实体表示形式";
        System.out.println(StringEscapeUtils.escapeHtml4(s));
        System.out.println(toHexString(s));

        System.out.println(StringEscapeUtils.escapeJava(s));
        String u = "&eacute;&raquo;&egrave;&reg;&curren;&egrave;&sect;&egrave;&sup2;2";
        String str = StringEscapeUtils.unescapeHtml3(u);
        System.out.println(new String(str.getBytes(), "GBK"));

    }


    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

}
