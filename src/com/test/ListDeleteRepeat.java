package com.test;

import java.util.*;

/**
 * Created by zhangfan on 2015/4/24.
 */
public class ListDeleteRepeat {

    public static void main(String[] args) {


        TestBean testBean = new TestBean("zhang1",1);
        TestBean testBean1 = new TestBean("zhang2",2);
        TestBean testBean2 = new TestBean("zhang3", 3);
        TestBean testBean3 = new TestBean("zhang4", 4);

        List<TestBean> list = new ArrayList();
        List<TestBean> list1 = new ArrayList();

        list.add(testBean);
        list.add(testBean1);
        list.add(testBean3);

        list1.add(testBean);
        list1.add(testBean1);
        list1.add(testBean3);
        list.add(testBean2);


        list.removeAll(list1);
        list.addAll(list1);


        Collections.sort(list, new Comparator<TestBean>() {
            @Override
            public int compare(TestBean o1, TestBean o2) {
                return o1.getNum() - o2.getNum();
            }
        });

        System.out.println(list);

    }
}


class TestBean{
    private String name;
    private Integer num;

    public TestBean() {
    }

    public TestBean(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestBean testBean = (TestBean) o;

        if (!num.equals(testBean.num)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return num.hashCode();
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
