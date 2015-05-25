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
        directorysList.add("src/main/java/com/userdefine");
        directorysList.add("src/main/java/com/userdefine/action");
        directorysList.add("src/main/java/com/userdefine/dao");
        directorysList.add("src/main/java/com/userdefine/model");
        directorysList.add("src/main/java/com/userdefine/service");
        directorysList.add("src/main/java/com/userdefine/util");

        directorysList.add("src/main/resources");
        directorysList.add("src/main/resources/mapper");

        directorysList.add("src/main/webapp");

        pathsMap = new HashMap<>();
        pathsMap.put("action", "src/main/java/com/userdefine/action/");
        pathsMap.put("dao", "src/main/java/com/userdefine/dao/");
        pathsMap.put("model", "src/main/java/com/userdefine/model/");
        pathsMap.put("service", "src/main/java/com/userdefine/service/");
        pathsMap.put("mapper", "src/main/resources/mapper/");


    }
    public static void execute(String path) throws Exception {

        for (String directory : directorysList) {
            File file = new File(path + directory);
            file.mkdirs();
        }


    }
}
