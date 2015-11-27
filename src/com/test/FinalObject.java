package com.test;

import javax.swing.plaf.basic.BasicMenuItemUI;
import java.math.BigDecimal;

/**
 * Created by zhangfan on 2015/3/19.
 */
public class FinalObject {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FinalObject{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        final FinalObject finalObject = new FinalObject();
        finalObject.setName("dddd");
        System.out.println(finalObject);

        System.out.println(new BigDecimal(0D));
    }
}
