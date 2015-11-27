package com.test.dbmysql.makeproject;


import java.util.List;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class MainThread {

    public static List<DBTableBean> dbTableBeans;
    public static String packageNameYourself = "rongrong"; //自定义package名  会影响javaBean xml 配置的生成  请保持和项目结构一样
    public static String path = "/Users/zhangfan/generateCode/"; //文件生成路径，和java包无关
    public static String dbUrl = "jdbc:mysql://127.0.0.1:3307/letsgo"; //数据库连接 + 库名
    public static String dbUserName = "root"; //数据库 用户名
    public static String dbPassword = "123456"; //数据库  密码

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

            GenerateJavaModel.execute(); //javaBena生成
            System.out.println("------------javaBean完成-----------");

            GenerateMyBatisXML.generateMyBatisXML(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), path + GenerateFrameWork.pathsMap.get("mapper"));//MybatisXML生成
            System.out.println("------------MybatisXML完成---------");

            GenerateDao.execute(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("dao")), MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")),
                    path + GenerateFrameWork.pathsMap.get("dao")); //Dao层接口生成 现只有    save update getBy
            System.out.println("------------DAO层接口完成-----------");

            GenerateProperties.execute(); //写入配置文件
            System.out.println("------------写入配置文件完成--------");

            GenerateTransactionManager.execute();//生成事务管理
            System.out.println("------------生成事务管理类----------");
            GenerateActionDB.execute();//生成简易可增删改查的数据库操作Action
            System.out.println("------------生成操作数据库Action----");

            GenerateJSP.execute();//生成数据库JSP管理
            System.out.println("------------生成数据库JSP管理页面---");

            GenerateJS.execute();//生成JSP对应JS页面
            System.out.println("------------生成JSP对应JS页面-------");

            System.out.println("\n------------已完成-----------------");


//            String[] cmd = new String[5];
//            cmd[0] = "cmd";
//            cmd[1] = "/c";
//            cmd[2] = "start";
//            cmd[3] = " ";
//            cmd[4] = path;
//            Runtime.getRuntime().exec(cmd);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
