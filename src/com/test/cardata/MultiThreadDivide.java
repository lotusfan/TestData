package com.test.cardata;

/**
 * Created by zhangfan on 16/1/21.
 */
public class MultiThreadDivide extends Thread {

    private int startNum = 0;
    private int endNum = 0;

    public MultiThreadDivide(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public static void divideParts(int count, int threadNum) {
        int temp = count / threadNum;

        int end = temp;
        int start = 0;

        for (int j = 0; j < threadNum; j++) {
            if (end > count) end = count;
            System.out.println(start + "-" + end);
            (new MultiThreadDivide(start, end)).start();
            start = end + 1;
            end += temp;
        }

    }

    @Override
    public void run() {

        System.out.println("补获数据");
        try {
            for (int i = startNum; i <= endNum; i++) {
                String responseData = SuiYiBeiData.sendRequest(i);

                while (true) {
                    if(SuiYiBeiData.queueData.offer(responseData)) break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
