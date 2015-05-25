package com.test.dbmysql.makeproject;

import java.util.List;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class MainThread {

    public static List<DBTableBean> dbTableBeans;


    /**
     * 生成的包名中有userdefine，可自行更换成自己需要的包
     *
     * @param args
     */


    public static void main(String[] args) {

        String path = "D://javaModel/"; //文件生成路径，和java包无关
        try {
            DBParse.execute(); //数据模块的初始化

            GenerateFrameWork.execute(path); //目录结构生成
            GenerateJavaModel.execute(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), path + GenerateFrameWork.pathsMap.get("model")); //javaBena生成
            GenerateMyBatisXML.generateMyBatisXML(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), path + GenerateFrameWork.pathsMap.get("mapper"));//MybatisXML生成
            GenerateDao.execute(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("dao")), MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")),
                    path + GenerateFrameWork.pathsMap.get("dao")); //Dao层接口生成 现只有    save update getBy
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
