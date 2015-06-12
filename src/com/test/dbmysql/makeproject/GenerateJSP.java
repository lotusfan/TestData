package com.test.dbmysql.makeproject;

import java.util.List;

/**
 * Created by zhangfan on 2015/6/1.
 */
public class GenerateJSP {


    public static void execute() {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            String tableName = dbTableBean.getTableName();
            List<String> columnName = dbTableBean.getColumnName();
            generateGetByJSP(tableName, columnName, dbTableBean.getPrimaryKey());
        }
    }


    public static void generateGetByJSP(String tableName, List<String> columnName, String primaryKey) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>\n" +
                "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>" + MainUtil.LineToUpper(tableName) + "DB</title>\n" +
                "    <script src=\"<c:url value=\"/jsdb/jquery-1.11.1.js\"/> \" language=\"JavaScript\"></script>\n" +
                "    <script src=\"<c:url value=\"/jsdb/jquery.json-2.4.js\"/> \" language=\"JavaScript\"></script>\n" +
                "    <script src=\"<c:url value=\"/jsdb/"+MainUtil.LineToUpper(tableName)+".js\"/> \" language=\"JavaScript\"></script>"+
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table>\n" +
                "    <tr>\n");
        for (String s : columnName) {
            stringBuffer.append("        <td>" + MainUtil.LineToUpper(s) + "</td>\n");
        }
        stringBuffer.append("    </tr>\n" +
                "    <c:forEach var=\"oo\" items=\"${" + MainUtil.LineToUpper(tableName) + "}\">\n" +
                "        <tr>\n");
        for (String s : columnName) {
            if (s.equals(primaryKey)) {
                stringBuffer.append("        <td><input class=\"oo" + MainUtil.LineToUpper(s) + "\" value=\"${oo." + MainUtil.LineToUpper(s) + "}\" readonly=\"true\" /></td>\n");
                continue;
            }
            stringBuffer.append("        <td><input class=\"oo" + MainUtil.LineToUpper(s) + "\" value=\"${oo." + MainUtil.LineToUpper(s) + "}\" /></td>\n");
        }
        stringBuffer.append("        <td><button class=\"alb\">修改</button></td>\n");

        stringBuffer.append("        </tr>\n" +
                "    </c:forEach>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        MainUtil.writeFile(MainUtil.LineToUpper(tableName)+ ".jsp", stringBuffer, MainThread.path + GenerateFrameWork.pathsMap.get("jsp"));
    }


}
