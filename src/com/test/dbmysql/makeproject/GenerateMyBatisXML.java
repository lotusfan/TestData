package com.test.dbmysql.makeproject;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by zhangfan on 2015/5/25.
 */
public class GenerateMyBatisXML {

    /**
     * Mybaties生成XML
     *
     * @param packageName
     * @throws Exception
     */

    public static void generateMyBatisXML(String packageName, String path) throws Exception {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            StringBuffer stringBuffer = new StringBuffer();
            List<String> columnName = dbTableBean.getColumnName();
            String tableName = dbTableBean.getTableName();

            stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            stringBuffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
            stringBuffer.append("<mapper namespace=\"");
            stringBuffer.append(packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)));
            stringBuffer.append("\">\n\n");

            generateResultMap(columnName, tableName, packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)), stringBuffer); //生成ResultMap
            generateInsertLable(columnName, tableName, packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)), "save", stringBuffer); //生成insert标签
            generateUpdateLable(columnName, tableName, packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)), "update", stringBuffer); //生成update标签
            generateSelectLable(columnName, tableName, packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)), "getBy",
                    MainUtil.LineToUpper(tableName) + "ResultMap", stringBuffer); //生成select标签
            generateCount(columnName, tableName, packageName + "." + MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)), "count", stringBuffer); //生成count标签


            stringBuffer.append("</mapper>");

            MainUtil.writeFile(MainUtil.UpperToFirst(MainUtil.LineToUpper(tableName)) + ".xml", stringBuffer, path); //写入文件

        }

    }


    /**
     * Mybatis生成resultMap标签
     *
     * @param type
     */
    public static void generateResultMap(List<String> columnName, String tableName, String type, StringBuffer stringBuffer) throws Exception {

        stringBuffer.append("<resultMap id=\"" + MainUtil.LineToUpper(tableName) + "ResultMap" + "\" type=\"" + type + "\">");

        for (String cn : columnName) {
            if (cn.equals("serialVersionUID")) continue;
            if (cn.equals("id")) {
                stringBuffer.append("\n\t<id column=\"id\" property=\"id\"/>");
                continue;
            }
            stringBuffer.append("\n\t<result column=\"" + cn + "\" property=\"" + MainUtil.LineToUpper(cn) + "\" />");
        }
        stringBuffer.append(" \n</resultMap> \n");

    }

    /**
     * Mybatis生成insert标签
     *
     * @param columnName
     * @param tableName
     * @param type
     * @param insertIdName
     */
    public static void generateInsertLable(List<String> columnName, String tableName, String type, String insertIdName, StringBuffer stringBuffer) {

        stringBuffer.append("<insert id=\"" + insertIdName + "\" parameterType=\"" + type + "\" useGeneratedKeys=\"true\" keyProperty=\"id\">");
        stringBuffer.append("\n\tinsert into `" + tableName + "`(");
        stringBuffer.append("\n\t<trim suffixOverrides=\",\">");
        for (String cn : columnName) {
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\">`" + cn + "`,</if>");
        }
        stringBuffer.append("\n\t</trim>\n\t)values(\n\t<trim suffixOverrides=\",\">");
        for (String cn : columnName) {
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\"> #{" + MainUtil.LineToUpper(cn) + "},</if>");
        }
        stringBuffer.append("\n\t</trim>\n\t)\n</insert>\n");

    }


    /**
     * Mybatis生成update标签
     *
     * @param columnName
     * @param tableName
     * @param type
     * @param updateIdName
     */
    public static void generateUpdateLable(List<String> columnName, String tableName, String type, String updateIdName, StringBuffer stringBuffer) {

        stringBuffer.append("<update id=\"" + updateIdName + "\" parameterType=\"" + type + "\">");
        stringBuffer.append("\n\tupdate `" + tableName + "`");
        stringBuffer.append("\n\t<trim prefix=\"set\" suffixOverrides=\",\">");
        for (String cn : columnName) {
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\">`" + cn + "` = #{" + MainUtil.LineToUpper(cn) + "},</if>");
        }
        stringBuffer.append("\n\t</trim>\n\twhere `id` = #{id}\n</update>\n");

    }

    /**
     * Mybatis生成select标签
     *
     * @param columnName
     * @param tableName
     * @param type
     * @param selectIdName
     * @param resultMapName
     * @param stringBuffer
     */
    public static void generateSelectLable(List<String> columnName, String tableName, String type, String selectIdName, String resultMapName, StringBuffer stringBuffer) {

        stringBuffer.append("<select id=\"" + selectIdName + "\" parameterType=\"" + type + "\" resultMap=\"" + resultMapName + "\">");
        stringBuffer.append("\n\tselect * from `" + tableName + "`");
        stringBuffer.append("\n\t<trim prefix=\"where\" prefixOverrides=\"AND|OR\">");
        for (String cn : columnName) {
            if (cn.equals("serialVersionUID")) continue;
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\">AND `" + cn + "` = #{" + MainUtil.LineToUpper(cn) + "} </if>");
        }
        stringBuffer.append("\n\t</trim>\n</select>\n");

    }

    public static void generatePaging(List<String> columnName, String tableName, String type, String pagingIdName, String resultMapName, StringBuffer stringBuffer) {

        stringBuffer.append("<select id=\"" + pagingIdName + "\" parameterType=\"" + type + "\" resultMap=\"" + resultMapName + "\">");
        stringBuffer.append("\n\tselect * from `" + tableName + "`");
        stringBuffer.append("\n\t<trim prefix=\"where\" prefixOverrides=\"AND|OR\">");
        for (String cn : columnName) {
            if (cn.equals("serialVersionUID")) continue;
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\">AND `" + cn + "` = #{" + MainUtil.LineToUpper(cn) + "} </if>");
        }
        stringBuffer.append("\n\t</trim>\n</select>\n");
    }


    /**
     * Mybatis生成count标签
     *
     * @param columnName
     * @param tableName
     * @param type
     * @param selectIdName
     * @param stringBuffer
     */
    public static void generateCount(List<String> columnName, String tableName, String type, String selectIdName, StringBuffer stringBuffer) {

        stringBuffer.append("<select id=\"" + selectIdName + "\" parameterType=\"" + type + "\" resultType=\"java.lang.Integer\">");
        stringBuffer.append("\n\tselect count(*) from `" + tableName + "`");
        stringBuffer.append("\n\t<trim prefix=\"where\" prefixOverrides=\"AND|OR\">");
        for (String cn : columnName) {
            if (cn.equals("serialVersionUID")) continue;
            if (cn.equals("id")) continue;
            stringBuffer.append("\n\t\t<if test=\"" + MainUtil.LineToUpper(cn) + " != null\">AND `" + cn + "` = #{" + MainUtil.LineToUpper(cn) + "} </if>");
        }
        stringBuffer.append("\n\t</trim>\n</select>\n");

    }
}
