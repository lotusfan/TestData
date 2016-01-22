package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 16/1/11.
 */
public class ArrayQueue {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 140; i++) {
            list.add(i + "");
            if (list.size() > 100) {
                list.removeAll(list.subList(0, 49));
            }
        }

        System.out.println(list);
        System.out.println(list.size());

    }
}
