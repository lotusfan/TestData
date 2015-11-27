package com.test.gjsono;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by zhangfan on 2015/6/12.
 */
public class ParentClassParseJson {


    public static void main(String[] args) {

        A a = new A();

        a.setName("zhangfan");
        a.setFace("face");

        Gson gson = new Gson();

        System.out.println(gson.toJson(a));

    }


}

class A extends B implements Serializable{


    private static final long serialVersionUID = -814922137231227530L;
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

class B {
    protected String sex;
    protected String face;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "B{" +
                "sex='" + sex + '\'' +
                ", face='" + face + '\'' +
                '}';
    }
}