package com.test.dbmysql.makeproject;

import java.util.*;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class GenerateJavaModel {

    public static Map<String, String> importPackageMap;

    //如有需要添加的import包  请在下面加关联
    static {
        importPackageMap = new HashMap<>();
        importPackageMap.put("BigDecimal", "java.math.BigDecimal");
        importPackageMap.put("BigInteger", "java.math.BigInteger");
        importPackageMap.put("Timestamp", "java.sql.Timestamp");
    }


    public static void execute(String packageName, String path) throws Exception {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            StringBuffer stringBuffer = new StringBuffer();

            //添加包头
            stringBuffer.append("package ");
            stringBuffer.append(packageName);
            stringBuffer.append(";\n\n");

            //import包     PS:待下面遍历出结果替换
            stringBuffer.append("importPackage");
            stringBuffer.append("\n");

            //类名
            stringBuffer.append("public class ");
            stringBuffer.append(MainUtil.UpperToFirst(MainUtil.LineToUpper(dbTableBean.getTableName())));
            stringBuffer.append(" {\n\n");

            List<String> columnName = dbTableBean.getColumnName();
            List<String> columnType = dbTableBean.getColumnType();
            List<String> columnRemarks = dbTableBean.getColumnRemarks();
            Set<String> importPackageSet = new HashSet<>(); //存放要import的包

            //成员变量
            for (int i = 0; i < columnName.size(); i++) {
                stringBuffer.append("\tprivate ");
                stringBuffer.append(columnType.get(i));
                stringBuffer.append(" ");
                stringBuffer.append(MainUtil.LineToUpper(columnName.get(i)));
                stringBuffer.append("; ");
                stringBuffer.append("//");
                stringBuffer.append(columnRemarks.get(i).replaceAll("\\s*|\\t|\\r|\\n", ""));
                stringBuffer.append("\n");

                if (importPackageMap.containsKey(columnType.get(i))) { //判定要添加的包
                    importPackageSet.add(importPackageMap.get(columnType.get(i)));
                }
            }

            //替换import 为真真的包
            StringBuffer importPackageSb = new StringBuffer();
            Iterator iterator = importPackageSet.iterator();
            while (iterator.hasNext()) {
                importPackageSb.append("import ");
                importPackageSb.append(iterator.next());
                importPackageSb.append(";\n");
            }
            int offset = stringBuffer.lastIndexOf("importPackage");
            stringBuffer.replace(offset, offset + 13, importPackageSb.toString());


            //get set方法
            stringBuffer.append("\n");
            for (int j = 0; j < columnName.size(); j++) {
                //get
                stringBuffer.append("\tpublic ");
                stringBuffer.append(columnType.get(j));
                stringBuffer.append(" get");
                stringBuffer.append(MainUtil.UpperToFirst(MainUtil.LineToUpper(columnName.get(j))));
                stringBuffer.append("() {\n");
                stringBuffer.append("\t\treturn ");
                stringBuffer.append(MainUtil.LineToUpper(columnName.get(j)));
                stringBuffer.append(";\n");
                stringBuffer.append("\t}\n");

                //set
                stringBuffer.append("\tpublic void");
                stringBuffer.append(" set");
                stringBuffer.append(MainUtil.UpperToFirst(MainUtil.LineToUpper(columnName.get(j))));
                stringBuffer.append(" (");
                stringBuffer.append(columnType.get(j));
                stringBuffer.append(" ");
                stringBuffer.append(MainUtil.LineToUpper(columnName.get(j)));
                stringBuffer.append(") {\n");
                stringBuffer.append("\t\tthis.");
                stringBuffer.append(MainUtil.LineToUpper(columnName.get(j)));
                stringBuffer.append(" = ");
                stringBuffer.append(MainUtil.LineToUpper(columnName.get(j)));
                stringBuffer.append(";\n");
                stringBuffer.append("\t}\n");

            }

            stringBuffer.append("}");
            MainUtil.writeFile(MainUtil.UpperToFirst(MainUtil.LineToUpper(dbTableBean.getTableName())) + ".java", stringBuffer, path);//写入文件
        }

    }


}
