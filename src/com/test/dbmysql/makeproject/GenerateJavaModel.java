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


    public static void execute() {
        generateJavaObjectModel(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), MainThread.path + GenerateFrameWork.pathsMap.get("model")); //生成javaModel
        generateParentModel(MainUtil.pathToPackageName(GenerateFrameWork.pathsMap.get("model")), MainThread.path + GenerateFrameWork.pathsMap.get("model")); //生成javaModel

    }


    public static void generateParentModel(String packageName, String path) {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("package " + packageName + ";\n" +
                "\n" +
                "public class ParentModel {\n" +
                "    private Integer skipNum;\n" +
                "    private Integer pageNum;\n" +
                "    private String sequence1;\n" +
                "    private String sequence1Type;\n" +
                "    private String sequence2;\n" +
                "    private String sequence2Type;\n" +
                "    private String maxParameter;\n" +
                "    private String maxValue;\n" +
                "    private String minParameter;\n" +
                "    private String minValue;\n" +
                "\n" +
                "    public Integer getSkipNum() {\n" +
                "        return skipNum;\n" +
                "    }\n" +
                "\n" +
                "    public void setSkipNum(Integer skipNum) {\n" +
                "        this.skipNum = skipNum;\n" +
                "    }\n" +
                "\n" +
                "    public Integer getPageNum() {\n" +
                "        return pageNum;\n" +
                "    }\n" +
                "\n" +
                "    public void setPageNum(Integer pageNum) {\n" +
                "        this.pageNum = pageNum;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public String getSequence1() {\n" +
                "        return sequence1;\n" +
                "    }\n" +
                "\n" +
                "    public void setSequence1(String sequence1) {\n" +
                "        this.sequence1 = sequence1;\n" +
                "    }\n" +
                "\n" +
                "    public String getSequence2() {\n" +
                "        return sequence2;\n" +
                "    }\n" +
                "\n" +
                "    public void setSequence2(String sequence2) {\n" +
                "        this.sequence2 = sequence2;\n" +
                "    }\n" +
                "\n" +
                "    public String getMaxParameter() {\n" +
                "        return maxParameter;\n" +
                "    }\n" +
                "\n" +
                "    public void setMaxParameter(String maxParameter) {\n" +
                "        this.maxParameter = maxParameter;\n" +
                "    }\n" +
                "\n" +
                "    public String getMaxValue() {\n" +
                "        return maxValue;\n" +
                "    }\n" +
                "\n" +
                "    public void setMaxValue(String maxValue) {\n" +
                "        this.maxValue = maxValue;\n" +
                "    }\n" +
                "\n" +
                "    public String getMinParameter() {\n" +
                "        return minParameter;\n" +
                "    }\n" +
                "\n" +
                "    public void setMinParameter(String minParameter) {\n" +
                "        this.minParameter = minParameter;\n" +
                "    }\n" +
                "\n" +
                "    public String getMinValue() {\n" +
                "        return minValue;\n" +
                "    }\n" +
                "\n" +
                "    public void setMinValue(String minValue) {\n" +
                "        this.minValue = minValue;\n" +
                "    }\n" +
                "\n" +
                "    public String getSequence1Type() {\n" +
                "        return sequence1Type;\n" +
                "    }\n" +
                "\n" +
                "    public void setSequence1Type(String sequence1Type) {\n" +
                "        this.sequence1Type = sequence1Type;\n" +
                "    }\n" +
                "\n" +
                "    public String getSequence2Type() {\n" +
                "        return sequence2Type;\n" +
                "    }\n" +
                "\n" +
                "    public void setSequence2Type(String sequence2Type) {\n" +
                "        this.sequence2Type = sequence2Type;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"ParentModel{\" +\n" +
                "                \"skipNum=\" + skipNum +\n" +
                "                \", pageNum=\" + pageNum +\n" +
                "                \", sequence1='\" + sequence1 + '\\'' +\n" +
                "                \", sequence1Type='\" + sequence1Type + '\\'' +\n" +
                "                \", sequence2='\" + sequence2 + '\\'' +\n" +
                "                \", sequence2Type='\" + sequence2Type + '\\'' +\n" +
                "                \", maxParameter='\" + maxParameter + '\\'' +\n" +
                "                \", maxValue='\" + maxValue + '\\'' +\n" +
                "                \", minParameter='\" + minParameter + '\\'' +\n" +
                "                \", minValue='\" + minValue + '\\'' +\n" +
                "                '}';\n" +
                "    }\n" +
                "}\n");


        MainUtil.writeFile("ParentModel.java", stringBuffer, path);//写入文件
    }

    public static void generateJavaObjectModel(String packageName, String path) {

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
            stringBuffer.append(" extends ParentModel{\n\n");

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

            //toString 方法
            stringBuffer.append("\n");
            stringBuffer.append("    @Override\n" +
                    "    public String toString() {\n" +
                    "        return \"" + MainUtil.UpperToFirst(MainUtil.LineToUpper(dbTableBean.getTableName())) + "{\" +\n");
            for (int j = 0; j < columnName.size(); j++) {
                stringBuffer.append("            \"\\n" + MainUtil.LineToUpper(columnName.get(j)) + "=\" + " + MainUtil.LineToUpper(columnName.get(j)) + " +\n");
            }

            stringBuffer.append("            '}';\n" +
                    "    }\n");

            stringBuffer.append("}");
            MainUtil.writeFile(MainUtil.UpperToFirst(MainUtil.LineToUpper(dbTableBean.getTableName())) + ".java", stringBuffer, path);//写入文件
        }

    }


}
