package com.test.dbmysql.makeproject;

import java.util.List;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class MainThread {

    public static List<DBTableBean> dbTableBeans;

    public static void main(String[] args) {

        try {
            DBParse.execute();

            GenerateJavaModel.execute("zhangfan");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
