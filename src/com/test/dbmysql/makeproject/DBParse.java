package com.test.dbmysql.makeproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class DBParse {


    public static Map<Integer, String> columnTypeMap = new HashMap<>();
    static {
        columnTypeMap.put(12, "String");
        columnTypeMap.put(1, "String");
        columnTypeMap.put(-4, "byte[]");
        columnTypeMap.put(-1, "String");
        columnTypeMap.put(4, "Long");
        columnTypeMap.put(-6, "Integer");
        columnTypeMap.put(5, "Integer");
        columnTypeMap.put(4, "Integer");
        columnTypeMap.put(-7, "Boolean");
        columnTypeMap.put(-5, "BigInteger");
        columnTypeMap.put(7, "Float");
        columnTypeMap.put(8, "Double");
        columnTypeMap.put(3, "BigDecimal");
        columnTypeMap.put(91, "Date");
        columnTypeMap.put(92, "Time");
        columnTypeMap.put(93, "Timestamp");
    }

    public static void execute() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "zhangfan", "123456");
            DatabaseMetaData metaData = conn.getMetaData();

            ResultSet tableSet = metaData.getTables(null, null, null, null);


            MainThread.dbTableBeans = new ArrayList<>();
            
            while (tableSet.next()) {
                DBTableBean dbTableBean = new DBTableBean();
                String tableName = tableSet.getString("TABLE_NAME");//ResultSet中固定位4为表名
                dbTableBean.setTableName(tableName);

                ResultSet primarySet = metaData.getPrimaryKeys(null, null, tableName); //获取表的主键
                while (primarySet.next()) {
                    dbTableBean.setPrimaryKey(primarySet.getNString("COLUMN_NAME"));
                }

                ResultSet columnSet = metaData.getColumns(null, null, tableName, null);

                List<String> columnName = new ArrayList<>();
                List<String> columnType = new ArrayList<>();
                List<String> columnRemarks = new ArrayList<>();
                while (columnSet.next()) {
                    columnName.add(columnSet.getString("COLUMN_NAME"));
                    columnType.add(columnTypeMap.get(columnSet.getInt("DATA_TYPE")));
                    columnRemarks.add(columnSet.getString("REMARKS"));
                }

                dbTableBean.setColumnName(columnName);
                dbTableBean.setColumnType(columnType);
                dbTableBean.setColumnRemarks(columnRemarks);

                MainThread.dbTableBeans.add(dbTableBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
