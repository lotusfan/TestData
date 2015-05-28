package com.test.dbmysql.makeproject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangfan on 2015/5/25.
 */
public class GenerateFrameWork {

    public static List<String> directorysList;
    public static Map<String, String> pathsMap;

    static {
        directorysList = new ArrayList<>();
        directorysList.add("src");
        directorysList.add("src/main");
        directorysList.add("src/main/java");
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself);
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself + "/action");
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself + "/dao");
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself + "/model");
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself + "/service");
        directorysList.add("src/main/java/com/" + MainThread.packageNameYourself + "/util");

        directorysList.add("src/main/resources");
        directorysList.add("src/main/resources/mapper");

        directorysList.add("src/main/webapp");

        pathsMap = new HashMap<>();
        pathsMap.put("action", "src/main/java/com/" + MainThread.packageNameYourself + "/action/");
        pathsMap.put("dao", "src/main/java/com/" + MainThread.packageNameYourself + "/dao/");
        pathsMap.put("model", "src/main/java/com/" + MainThread.packageNameYourself + "/model/");
        pathsMap.put("service", "src/main/java/com/" + MainThread.packageNameYourself + "/service/");
        pathsMap.put("mapper", "src/main/resources/mapper/");
        pathsMap.put("resources", "src/main/resources/");
        pathsMap.put("webapp", "src/main/webapp/");


    }

    public static void execute() throws Exception {

        for (String directory : directorysList) {
            File file = new File(MainThread.path + directory);
            file.mkdirs();
        }


    }
}
