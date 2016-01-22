package com.test.dbmysql;

import java.lang.reflect.Field;

/**
 * Created by zhangfan on 2015/3/5.
 */
public class GenerateMybatisXMLMain {
    public static void main(String[] args) throws Exception {

        Class cl = Class.forName("com.mengyou.model.db.Transaction");
        String type = cl.getName();
        String resultMapName = LowerToFirst(cl.getSimpleName()) + "ResultMap";

        String insertIdName = "save";
        String dbName = "transaction";

        String updateIdName = "update";

        String selectIdName = "getBy";


        Field[] fields = cl.getDeclaredFields();

        generateResultMap(fields, resultMapName, type); //生成ResultMap

        generateInsertLable(fields, insertIdName, type, dbName); //生成insert标签

        generateUpdateLable(fields, updateIdName, type, dbName); //生成update标签

        generateSelectLable(fields, selectIdName, type, dbName, resultMapName);
    }

    /**
     * Mybatis生成resultMap标签
     *
     * @param fileds
     * @param resultMapName
     * @param type
     */
    public static void generateResultMap(Field[] fileds, String resultMapName, String type) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<resultMap id=\"" + resultMapName + "\" type=\"" + type + "\">");
        for (Field field : fileds) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (field.getName().equals("id")) {
                stringBuffer.append("\n\t<id column=\"id\" property=\"id\"/>");
                continue;
            }
            stringBuffer.append("\n\t<result column=\"" + UpperToLine(field.getName()) + "\" property=\"" + field.getName() + "\" />");
        }
        stringBuffer.append(" \n</resultMap>");
        System.out.println(stringBuffer.toString());
    }

    /**
     * Mybatis生成insert标签
     *
     * @param fields
     * @param insertIdName
     * @param type
     * @param dbName
     */
    public static void generateInsertLable(Field[] fields, String insertIdName, String type, String dbName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<insert id=\"" + insertIdName + "\" parameterType=\"" + type + "\" useGeneratedKeys=\"true\" keyProperty=\"id\">");
        stringBuffer.append("\n\tinsert into `" + dbName + "`(");
        stringBuffer.append("\n\t<trim suffixOverrides=\",\">");
        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (field.getName().equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + field.getName() + " != null\">`" + UpperToLine(field.getName()) + "`,</if>");
        }
        stringBuffer.append("</trim>\n\t)values(\n\t<trim suffixOverrides=\",\">");
        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (field.getName().equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + field.getName() + " != null\"> #{" + field.getName() + "},</if>");
        }
        stringBuffer.append("\n\t</trim>\n\t)\n</insert>");

        System.out.println(stringBuffer.toString());
    }

    /**
     * Mybatis生成Update标签
     *
     * @param fields
     * @param updateIdName
     * @param type
     * @param dbName
     */
    public static void generateUpdateLable(Field[] fields, String updateIdName, String type, String dbName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<update id=\"" + updateIdName + "\" parameterType=\"" + type + "\">");
        stringBuffer.append("\n\tupdate `" + dbName + "`");
        stringBuffer.append("\n\t<trim prefix=\"set\" suffixOverrides=\",\">");
        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (field.getName().equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + field.getName() + " != null\">`" + UpperToLine(field.getName()) + "` = #{" + field.getName() + "},</if>");
        }
        stringBuffer.append("\n\t</trim>\n\twhere `id` = #{id}\n</update>");
        System.out.println(stringBuffer.toString());

    }

    /**
     * Mybatis生成select标签
     *
     * @param fields
     * @param selectIdName
     * @param type
     * @param dbName
     * @param resultMapName
     */
    public static void generateSelectLable(Field[] fields, String selectIdName, String type, String dbName, String resultMapName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<select id=\"" + selectIdName + "\" parameterType=\"" + type + "\" resultMap=\"" + resultMapName + "\">");
        stringBuffer.append("\n\tselect * from `" + dbName + "`");
        stringBuffer.append("\n\t<trim prefix=\"where\" prefixOverrides=\"AND|OR\">");
        for (Field field : fields) {
            if (field.getName().equals("serialVersionUID")) continue;
            if (field.getName().equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + field.getName() + " != null\">AND `" + UpperToLine(field.getName()) + "` = #{" + field.getName() + "} </if>");
        }
        stringBuffer.append("\n\t</trim>\n</select>");
        System.out.println(stringBuffer.toString());

    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String LowerToFirst(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str.replace(str.charAt(0), Character.toLowerCase(str.charAt(0)));
        }
        return str;
    }

    /**
     * 大写字母转换成“_小写”
     *
     * @param str
     * @return
     */
    public static String UpperToLine(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                str = str.replaceFirst(str.charAt(i) + "", "_" + Character.toLowerCase(str.charAt(i)));
                i++;
            }
        }
        return str;
    }
}
