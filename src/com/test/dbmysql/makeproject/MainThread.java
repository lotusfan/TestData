package com.test.dbmysql.makeproject;


import java.util.List;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class MainThread {

    public static List<DBTableBean> dbTableBeans;
    public static String packageNameYourself = "userdefine"; //自定义package名  会影响javaBean xml 配置的生成  请保持和项目结构一样
    public static String path = "D://javaModel/"; //文件生成路径，和java包无关

    /**
     * 生成的包名中有userdefine，可自行更换成自己需要的包
     *
     * @param args
     */


    public static void main(String[] args) {


        try {
            System.out.println("------------构建项目开始------------\n");
            DBParse.execute(); //数据模块的初始化
            System.out.println("------------数据模块完成初始化------");

            GenerateFrameWork.execute(); //目录结构生成
            System.out.println("------------目录结构完成------------");

            GenerateJavaModel.execute(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), path + GenerateFrameWork.pathsMap.get("model")); //javaBena生成
            System.out.println("------------javaBean完成-----------");

            GenerateMyBatisXML.generateMyBatisXML(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), path + GenerateFrameWork.pathsMap.get("mapper"));//MybatisXML生成
            System.out.println("------------MybatisXML完成---------");

            GenerateDao.execute(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("dao")), MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")),
                    path + GenerateFrameWork.pathsMap.get("dao")); //Dao层接口生成 现只有    save update getBy
            System.out.println("------------DAO层接口完成----------");

            GenerateProperties.execute(); //写入配置文件
            System.out.println("------------写入配置文件完成-------");

            GenerateActionDB.execute();//生成简易可增删改查的数据库操作Action
            System.out.println("------------生成操作数据库Action---");

            GenerateJSP.execute();//生成数据库JSP管理
            System.out.println("------------生成数据库JSP管理页面--");

            GenerateJS.execute();//生成JSP对应JS页面
            System.out.println("------------生成JSP对应JS页面------");

            System.out.println("\n------------已完成-----------------");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
