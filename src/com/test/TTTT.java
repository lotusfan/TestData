package com.test;

import com.test.dbmysql.makeproject.MainThread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by zhangfan on 2015/5/14.
 */
public class TTTT {
    public static void main(String[] args) {


        String s = "BCEC44ED-0FB2-4ACF-84D5-13DCD95F7A3A";
        System.out.println(s.length());


        /*Set set = new HashSet();
        for (int i = 0; i < 100000; i++) {

            set.add(getRandomCharArray(6).toLowerCase());
        }
        System.out.println(set.size());*/


//        System.out.println(Math.floor(12 / Float.parseFloat("5")));


       /* Matcher matcher=Pattern.compile("\\((\\d{7})\\)").matcher("2015-06-04(2015064)");
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }*/

 /*       int[][] ints = new int[1][22];
        ints[0][2]++;

        System.out.println(ints[0][2]);*/


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


    public static String getRandomCharArray(int iLength) {
        String val = "";

        Random random = new Random();
        for(int i = 0; i < iLength; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                char temp = (char) (choice + random.nextInt(26));
                if(String.valueOf(temp).equals("l")||String.valueOf(temp).equals("L")||String.valueOf(temp).equals("O")||String.valueOf(temp).equals("o")){
                    temp='a';
                }
                val += temp;
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                int temp = random.nextInt(10);
                //剔除0和1
                if(temp<2){
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                    val += (char) (choice + random.nextInt(26));
                }else {
                    val += temp;
                }
            }
        }

        return val;
    }
}
