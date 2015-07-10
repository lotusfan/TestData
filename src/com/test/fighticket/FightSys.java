package com.test.fighticket;

import java.util.Random;

/**
 * Created by zhangfan on 2015/6/18.
 */
public class FightSys {

    public static boolean swithc = false;
    public static int ticketCount = 0;



    /**
     * 抢票
     *
     * @return
     */
    public static synchronized boolean fight() {


        Random random = new Random();

        int i = random.nextInt(10);

        if (i % 2 == 0) {
            ticketCount--;
            return true;
        }

        return false;
    }

    /**
     * 开启系统
     *
     * @return
     */
    public static synchronized boolean startSys(){
        FightSys.swithc = true;
        System.out.println("抢票系统："+ FightSys.swithc);
        return FightSys.swithc;
    }

    /**
     * 关闭系统
     *
     * @return
     */
    public static synchronized boolean shutdownSys(){
        swithc = false;
        return swithc;
    }


    /**
     * 设置库存票数
     *
     * @param i
     * @return
     */
    public static synchronized int setTicketCount(int i){
        ticketCount = i;
        return ticketCount;
    }


    public static boolean getSwitch() {
        return swithc;
    }

}
