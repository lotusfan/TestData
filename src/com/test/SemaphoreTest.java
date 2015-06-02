package com.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangfan on 2015/6/2.
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            final int num = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("---------顺序---------" + num);
                        Thread.sleep(5 * 1000);
                        semaphore.release();

                        System.out.println("---------" + semaphore.availablePermits());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };

            executorService.execute(runnable);
        }




    }
}
