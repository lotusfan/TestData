package com.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2015/5/14.
 */
public class TTTT {
    public static void main(String[] args) {
        try {

            File file = new File("F:\\KuGou");

            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1.getName());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        List<String> strings = new ArrayList<String>();
        strings.add("333");
        strings.add("444");
        strings.add(1, "999");
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
