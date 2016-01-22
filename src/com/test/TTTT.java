package com.test;

import com.test.dbmysql.makeproject.MainThread;

import java.io.File;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangfan on 2015/5/14.
 */
public class TTTT {
    public static void main(String[] args) {

        String temp = "验证码：${check_code}，您正在使用全融短信验证码进行校验，请勿告知他人。客服电话：4000-686-000";
        String content = temp.replace("${check_code}", "asdfasdf");
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
        String str1 = "appid=wxdfe7743986030fcc&body=letsgao&mch_id=1287883601&nonce_str=B31A21E25F1C4E97BE494FA6DCA84D58&notify_url=http://58.132.171.184/letsgo&out_trade_no=20151207test&spbill_create_ip=58.132.171.184&total_fee=1&trade_type=APP&key=d4624c36b6795d1d99dcf0547af5443d";
        String str2 = "appid=wxdfe7743986030fcc&body=letsgao&mch_id=1287883601&nonce_str=B31A21E25F1C4E97BE494FA6DCA84D58&notify_url=http://58.132.171.184/letsgo&out_trade_no=20151207test&spbill_create_ip=58.132.171.184&total_fee=1&trade_type=APP&key=d4624c36b6795d1d99dcf0547af5443d";
        System.out.println(str1.equals(str2));

       /* Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        System.out.println(calendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(calendar.getTime()));*/

     /*   String str = "";
        System.out.println(str.length());*/

       /* String str = "D:\\javaModel\\";
        String s = "newp2p\\WEB-INF\\classes\\com\\hurong\\credit\\action\\message";
        File file = new File(str + s);
        file.mkdirs();*/


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
