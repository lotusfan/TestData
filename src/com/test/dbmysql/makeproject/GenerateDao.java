package com.test.dbmysql.makeproject;

/**
 * Created by zhangfan on 2015/5/25.
 */
public class GenerateDao {

    public static void execute(String packageName, String beanPackage, String path) {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            String parameterName = MainUtil.LineToUpper(dbTableBean.getTableName());
            String className = MainUtil.UpperToFirst(parameterName);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("package ");
            stringBuffer.append(packageName);
            stringBuffer.append(";\nimport ");
            stringBuffer.append(beanPackage + "." + className);
            stringBuffer.append(";\nimport java.util.List;\n");

            stringBuffer.append("public interface ");
            stringBuffer.append(className+"Mapper");
            stringBuffer.append("{\n");

            stringBuffer.append("\tpublic void save (");
            stringBuffer.append(className + " " + parameterName + ");\n");
            stringBuffer.append("\tpublic void update (");
            stringBuffer.append(className + " " + parameterName + ");\n");
            stringBuffer.append("\tpublic List<" + className + "> getBy (");
            stringBuffer.append(className + " " + parameterName + ");\n");
            stringBuffer.append("\tpublic " + className + " getUniqueBy (");
            stringBuffer.append(className + " " + parameterName + ");\n");
            stringBuffer.append("\tpublic Integer count (");
            stringBuffer.append(className + " " + parameterName + ");\n");

            stringBuffer.append("}");
            MainUtil.writeFile(className + "Mapper.java", stringBuffer, path); //写入文件
        }

    }
}
