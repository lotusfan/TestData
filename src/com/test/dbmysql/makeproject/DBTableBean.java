package com.test.dbmysql.makeproject;

import java.util.List;

/**
 * Created by zhangfan on 2015/5/22.
 */
public class DBTableBean {
    private String tableName;
    private List<String> columnName;
    private List<String> columnType;
    private List<String> columnRemarks;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public List<String> getColumnType() {
        return columnType;
    }

    public void setColumnType(List<String> columnType) {
        this.columnType = columnType;
    }

    public List<String> getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(List<String> columnRemarks) {
        this.columnRemarks = columnRemarks;
    }

    @Override
    public String toString() {
        return "DBTableBean{" +
                "tableName='" + tableName + '\'' +
                ", columnName=" + columnName +
                ", columnType=" + columnType +
                ", columnRemarks=" + columnRemarks +
                '}';
    }
}
