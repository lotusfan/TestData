package com.test;

/**
 * Created by zhangfan on 2015/4/21.
 */
public class StringInvertedSequence {

    public static void main(String[] args) {
        String str = "Our company is crtip.";
        str = str.substring(0, str.length()-1);
        String[] strs = str.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        strs[strs.length-1] = upperToFirst(strs[strs.length - 1]);
        strs[0] = lowerToFirst(strs[0]);
        for (int i = strs.length - 1; i >= 0; i--) {
            stringBuffer.append(strs[i]);
            stringBuffer.append(" ");
        }
        stringBuffer.setCharAt(stringBuffer.length() - 1, '.');
        System.out.println(stringBuffer.toString());

    }

    /**
     * 首字母大写
     */
    public static String upperToFirst(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str.replace(str.charAt(0), Character.toUpperCase(str.charAt(0)));
        }
        return str;
    }
    /**
     * 首字母小写
     */
    public static String lowerToFirst(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str.replace(str.charAt(0), Character.toLowerCase(str.charAt(0)));
        }
        return str;
    }
}
