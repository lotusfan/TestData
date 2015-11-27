package com.test.dbmysql.makeproject;

import java.util.List;

/**
 * Created by zhangfan on 2015/6/2.
 */
public class GenerateJS {

    public static void execute() {

        for (DBTableBean dbTableBean : MainThread.dbTableBeans) {

            String tableName = dbTableBean.getTableName();
            List<String> columnName = dbTableBean.getColumnName();

            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("$(document).ready(function () {\n" +
                    "\n" +
                    "    $(\".alb\").click(function(){\n" +
                    "        var $currentel = $(this).parent().parent();\n" +
                    "\n" +
                    "        var oo = new Object();\n");

            for (String s : columnName) {

                stringBuffer.append("        oo." + MainUtil.LineToUpper(s) + "=$currentel.find(\".oo" + MainUtil.LineToUpper(s) + "\").val();\n");
            }



            stringBuffer.append(
                    "        $.ajax({\n" +
                    "            url: \"/db/"+MainUtil.LineToUpper(tableName)+"/update.do\",\n" +
                    "            type: \"POST\",\n" +
                    "            data: $.toJSON(oo),\n" +
                    "            dataType: \"json\",\n" +
                    "            contentType:\"application/json\",\n" +
                    "            success: function (data) {\n" +
                    "                alert(data.msg);\n" +
                    "            },\n" +
                    "            error: function () {\n" +
                    "                alert(\"请求失败\");\n" +
                    "            }\n" +
                    "        });\n" +
                    "    });\n" +
                    "});");
            MainUtil.writeFile(MainUtil.LineToUpper(tableName) + ".js", stringBuffer, MainThread.path + GenerateFrameWork.pathsMap.get("js"));
        }
    }


}
