package com.test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zhangfan on 2015/9/15.
 */
public class ResourceBundleTest {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("jeesite");

    public static String getKey(String key) {

        return BUNDLE.getString(key);
    }


    public static void main(String[] args) throws InterruptedException {

//        ResourceBundle.clearCache(BUNDLE.getClass().getClassLoader());
//        ResourceBundle.getBundle("jeesite", new Locale("3"), BUNDLE.getClass().getClassLoader());
        System.out.println(ResourceBundleTest.getKey("jdbc.driver"));
        ResourceBundle.clearCache();
        Thread.sleep(10 * 1000);
        System.out.println(ResourceBundleTest.getKey("jdbc.driver"));
    }
}
