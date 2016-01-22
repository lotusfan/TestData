package com.test.gjsono;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.StringMap;

import java.sql.Timestamp;

/**
 * Created by zhangfan on 2015/4/22.
 */
public class GjsonToObject {

    public static void main(String[] args) {
       /* JavaBeanTest javaBean = new JavaBeanTest();
        SubBeanTest subBeanTest = new SubBeanTest();
        subBeanTest.setAge(3);
        subBeanTest.setSex("");
        javaBean.setName("eeeee");
        javaBean.setObject(subBeanTest);
//        javaBean.setObject("");
        Gson gson = new Gson();

        String str = gson.toJson(javaBean).toString();
        System.out.println(str);

        //fastjson
        JavaBeanTest javaBeanTest1 = JSON.toJavaObject(JSON.parseObject(str), JavaBeanTest.class);
        System.out.println(javaBeanTest1.getObject().getClass().getName());
        SubBeanTest subBeanTest1 = (SubBeanTest)JSON.toJavaObject((com.alibaba.fastjson.JSON)javaBeanTest1.getObject(), SubBeanTest.class);
        System.out.println(subBeanTest1);

        JavaBeanTest javaBeanTest = gson.fromJson(gson.toJson(javaBean), JavaBeanTest.class);
        System.out.println(javaBeanTest);*/
       /* System.out.println(javaBeanTest.getObject().getClass().getName());
        System.out.println(gson.toJson(javaBeanTest.getObject()));
        SubBeanTest subBean = gson.fromJson(gson.toJson(javaBeanTest.getObject()), SubBeanTest.class);

        System.out.println(subBean.getAge());*/

        SubBeanTest subBeanTest = new SubBeanTest();
//        subBeanTest.setAge(3);
        Gson gson = new Gson();
        try {
            System.out.println(gson.fromJson("{'age':3, 'sex':null, 'timestamp':''}", SubBeanTest.class));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

class JavaBeanTest{
    private String name;
    private Object object;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "JavaBeanTest{" +
                "name='" + name + '\'' +
                ", object=" + object +
                '}';
    }
}
class SubBeanTest{
    private Integer age;
    private String sex;
    private String sexx;
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "SubBeanTest{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                ", sexx='" + sexx + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
