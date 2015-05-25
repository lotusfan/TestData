package com.test.dbmysql.makeproject;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.File;
import java.io.FileOutputStream;
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


    public static void execute(String packageName) throws Exception {

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
            stringBuffer.append(UpperToFirst(LineToUpper(dbTableBean.getTableName())));
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
                stringBuffer.append(LineToUpper(columnName.get(i)));
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
                stringBuffer.append(UpperToFirst(LineToUpper(columnName.get(j))));
                stringBuffer.append("() {\n");
                stringBuffer.append("\t\treturn ");
                stringBuffer.append(LineToUpper(columnName.get(j)));
                stringBuffer.append(";\n");
                stringBuffer.append("\t}\n");

                //set
                stringBuffer.append("\tpublic void");
                stringBuffer.append(" set");
                stringBuffer.append(UpperToFirst(LineToUpper(columnName.get(j))));
                stringBuffer.append(" (");
                stringBuffer.append(columnType.get(j));
                stringBuffer.append(" ");
                stringBuffer.append(LineToUpper(columnName.get(j)));
                stringBuffer.append(") {\n");
                stringBuffer.append("\t\tthis.");
                stringBuffer.append(LineToUpper(columnName.get(j)));
                stringBuffer.append(" = ");
                stringBuffer.append(LineToUpper(columnName.get(j)));
                stringBuffer.append(";\n");
                stringBuffer.append("\t}\n");

            }

            stringBuffer.append("}");
            System.out.println(stringBuffer.toString());
            writeFile(UpperToFirst(LineToUpper(dbTableBean.getTableName())), stringBuffer);
        }

    }


    public static void writeFile(String name, StringBuffer stringBuffer) {

        FileOutputStream fileOutputStream = null;
        try {

            String path = "D://javaModel/";
            File file = new File(path + name + ".java");

            byte[] bytes = stringBuffer.toString().getBytes();
            ByteInputStream byteInputStream = new ByteInputStream(bytes, bytes.length);
            fileOutputStream = new FileOutputStream(file);

            int i = 0;
            while ((i = byteInputStream.read()) > 0) {

                fileOutputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * “_小”转换为大写字母
     *
     * @param str
     * @return
     */
    public static String LineToUpper(String str) {

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == '_' && i < chars.length - 1) {
                i++;
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }

        return new String(chars).replaceAll("_", "");
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String UpperToFirst(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str.replaceFirst(str.charAt(0) + "", Character.toUpperCase(str.charAt(0)) + "");
        }
        return str;
    }
}
