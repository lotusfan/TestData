package com.test.threadsq;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zhangfan on 2015/6/11.
 */
public class ThreadsQueueTest {

    public static int m = 0;
    public static List<Integer> integers = new ArrayList<>();
    public static void main(String[] args) {

        final StringQueue stringQueue = new StringQueue();
        final IntegerPar integerPar = new IntegerPar();


        for (int j = 20; j > 0; j--) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        while (true) {
                            stringQueue.set(integerPar.get() + "");
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        for (int i = 20; i > 0; i--) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(Thread.currentThread().getName() + "\t" + stringQueue.get());
//                            integers.add(Integer.parseInt(stringQueue.get()));

                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}

class StringQueue {

    private ArrayDeque<String> stringQueue = new ArrayDeque<>();


    public synchronized String get() {
        return stringQueue.pollFirst();
    }

    public synchronized void set(String str) {
        stringQueue.add(str);
    }
}
class IntegerPar{
    private Integer m = 0;

    public synchronized  int get() {
        return m++;
    }
}