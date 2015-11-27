package com.test.dbmysql.makeproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2015/5/28.
 */
public class GenerateProperties {

    public static List<String> stringList = new ArrayList<>();

    public static void execute() throws Exception {


        searchFile();

        for (String s : stringList) {

            File file = new File(s);
            File[] files = file.listFiles();
            String fileName = file.getName();


            if (fileName.equals("pom")) {

                writeFile(files[0], MainThread.path);

            }
            if (fileName.equals("properties")) {

                for (File f : files) {
                    writeFile(f, MainThread.path + GenerateFrameWork.pathsMap.get("resources"));
                }
            }
            if (fileName.equals("web")) {

                writeFile(files[0], MainThread.path+GenerateFrameWork.pathsMap.get("webapp"));
            }
            if (fileName.equals("js")) {
                for (File f : files) {
                    writeFile(f, MainThread.path + GenerateFrameWork.pathsMap.get("js"));
                }
            }

        }

    }


    /**
     * 查找pom properties  web文件
     *
     */
    public static void searchFile() {

        File file = new File(GenerateProperties.class.getResource("/").getFile());

        searchLoop(file);

    }

    /**
     * 递归函数
     *
     * @param file
     */
    public static void searchLoop(File file) {

        if (!file.isDirectory()) return;
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return;
        for (File f : files) {
            if (f.getName().matches("pom|properties|web|js"))
                stringList.add(f.getAbsolutePath());
            searchLoop(f);
        }

    }


    /**
     * 写入对应文件
     *
     * @param file
     * @param path
     */
    public static void writeFile(File file, String path) {

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {

            fileInputStream = new FileInputStream(file);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String str = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str+"\n");
            }

            int i = 0;
            //包名替换
            while ((i = stringBuffer.lastIndexOf("userdefine")) > 0) {
                stringBuffer.replace(i, i + 10, MainThread.packageNameYourself);
            }
            MainUtil.writeFile(file.getName(), stringBuffer, path);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
