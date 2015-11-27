package com.test.dbmysql.makeproject;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class test {

    public String aa;

    public String getAa() {
        System.out.println("get");
        return aa;
    }

    public void setAa(String aa) {
        System.out.println("set");
        this.aa = aa;
    }

    public static void main(String[] args) throws Exception {
        /*Class c = Class.forName("java.math.BigDecimal");
        System.out.println(c.getName());*/


        test test = new test();

        test.aa = "dd";
        System.out.println(test.aa);



    }
}
