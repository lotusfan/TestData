package com.test.doubleball;

import javax.swing.text.html.FormSubmitEvent;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangfan on 2015/6/5.
 */
public class Probabilistic {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(new File("D://doubleball.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        int[][] ints = new int[1][34];

        String str = null;
        String temp = null;

        Pattern patternRed = Pattern.compile("red\\[(\\d{2})\\]");
        Pattern patternBlu = Pattern.compile("blu[(\\d{2}]]");

        while ((str = bufferedReader.readLine()) != null) {

            Matcher matcherRed = patternRed.matcher(str);

            while (matcherRed.find()) {
                temp = matcherRed.group(1);
                ints[0][Integer.parseInt(temp)]++;
            }
        }

        int sum = 0;
        for (int j = 1; j < 33; j++) {
            System.out.print(j + "号球：");
            sum += ints[0][j];
            System.out.println(ints[0][j]);
        }

        for (int j = 1; j < 33; j++) {
            System.out.print(j + "号球概率：");
            System.out.println(ints[0][j] / Float.parseFloat(sum + "")*100 + "%");
        }


    }

}
