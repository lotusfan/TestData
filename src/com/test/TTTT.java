package com.test;

import com.test.dbmysql.makeproject.MainThread;

/**
 * Created by zhangfan on 2015/5/14.
 */
public class TTTT {
    public static void main(String[] args) {

//        System.out.println(Math.floor(12 / Float.parseFloat("5")));


       /* Matcher matcher=Pattern.compile("\\((\\d{7})\\)").matcher("2015-06-04(2015064)");
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }*/

        int[][] ints = new int[1][22];
        ints[0][2]++;

        System.out.println(ints[0][2]);


        /*try {

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
        }*/
    }
}
