package com.test.dbmysql.makeproject;

/**
 * Created by zhangfan on 2015/6/12.
 */
public class GenerateTransactionManager {


    public static void execute() {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            String parameterName = MainUtil.LineToUpper(dbTableBean.getTableName());
            String className = MainUtil.UpperToFirst(parameterName);
            String path = MainThread.path + GenerateFrameWork.pathsMap.get("transaction");
            String packageName = MainThread.packageNameYourself;

            generateTransactionManager(packageName, className, parameterName, path);
        }


    }


    /**
     * 生成transactionManager事务类
     *
     * @param packageName
     * @param className
     * @param parameterName
     * @param path
     */
    public static void generateTransactionManager(String packageName, String className, String parameterName, String path) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("package com." + packageName + ".service.transactionmanager;\n" +
                "\n" +
                "import com." + packageName + ".dao." + className + "Mapper;\n" +
                "import com." + packageName + ".model." + className + ";\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Service\n" +
                "public class " + className + "TransactionManager {\n" +
                "\n" +
                "\n" +
                "\n" +
                "    @Autowired\n" +
                "    private " + className + "Mapper " + parameterName + "Mapper;\n" +
                "\n" +
                "\n" +
                "    public void save" + className + "(" + className + " " + parameterName + ") {\n" +
                "\n" +
                "        " + parameterName + "Mapper.save(" + parameterName + ");\n" +
                "    }\n" +
                "\n" +
                "    public void save" + className + "s(List<" + className + "> " + parameterName + "s) {\n" +
                "\n" +
                "        if (" + parameterName + "s == null) {\n" +
                "            return;\n" +
                "        }\n" +
                "        for (int i = 0; i < " + parameterName + "s.size(); i++) {\n" +
                "            " + parameterName + "Mapper.save(" + parameterName + "s.get(i));\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void update" + className + "(" + className + " " + parameterName + ") {\n" +
                "        " + parameterName + "Mapper.update(" + parameterName + ");\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    public void update" + className + "s(List<" + className + "> " + parameterName + "s) {\n" +
                "        if (" + parameterName + "s == null) {\n" +
                "            return;\n" +
                "        }\n" +
                "        for (int i = 0; i < " + parameterName + "s.size(); i++) {\n" +
                "            " + parameterName + "Mapper.update(" + parameterName + "s.get(i));\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public " + className + " getUniqueBy(" + className + " " + parameterName + ") {\n" +
                "\n" +
                "        return " + parameterName + "Mapper.getUniqueBy(" + parameterName + ");\n" +
                "    }\n" +
                "    public List<" + className + "> getConditionBy(" + className + " " + parameterName + ") {\n" +
                "\n" +
                "        return " + parameterName + "Mapper.getConditionBy(" + parameterName + ");\n" +
                "    }\n" +
                "\n" +
                "    public List<" + className + "> getBy(" + className + " " + parameterName + ") {\n" +
                "\n" +
                "        return " + parameterName + "Mapper.getBy(" + parameterName + ");\n" +
                "    }\n" +
                "\n" +
                "    public Integer count(" + className + " " + parameterName + ") {\n" +
                "\n" +
                "        return " + parameterName + "Mapper.count(" + parameterName + ");\n" +
                "    }\n" +
                "\n" +
                "}\n");


        MainUtil.writeFile(className + "TransactionManager.java", stringBuffer, path);

    }

}
