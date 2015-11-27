package com.test.dbmysql;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhangfan on 2015/5/13.
 */
public class GetTableNameColunm {

    public static Map<Integer, String> colunmTypeMap = new HashMap<Integer, String>();
    ;

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);

        generateColumnsType();

        try {
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "zhangfan", "123456");

            stmt = (Statement) conn.createStatement();

            getTableNameByCon(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void getTableNameByCon(Connection con) {
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[]{"TABLE"});
            while (rs.next()) {
                System.out.println("表名：" + rs.getString(3));
                System.out.println("表所属用户名：" + rs.getString(2));
                System.out.println("类型： " + rs.getString(4));


                ResultSet resultSet = meta.getColumns(null, null, rs.getString(3), null);
                while (resultSet.next()) {
                    System.out.printf("列名：%-15s ", resultSet.getString(4));
                    System.out.printf("\t列类型：%-15s ", colunmTypeMap.get(resultSet.getInt(5)));
                    System.out.println("\t长度： " + resultSet.getString(7));
                }
                System.out.println("------------------------------");
            }


            con.close();
        } catch (Exception e) {
            try {
                con.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void generateColumnsType() {
        try {
            Field[] fields = Types.class.getFields();
            for (Field field : fields) {
                colunmTypeMap.put(field.getInt(field.getName()), field.getName());
                colunmTypeMap.get(field.getInt(field.getName()) + "3333");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
