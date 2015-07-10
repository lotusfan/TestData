package com.test.fighticket;

import java.util.Scanner;

/**
 * Created by zhangfan on 2015/6/18.
 */
public class FightTicketMain {

    public static void main(String[] args)  throws Exception{


        FightTimeTaskStartSys.execute();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("输入1进行抢票");
            System.out.println(FightSys.swithc);
            System.out.println(FightSys.ticketCount);
            int i = scanner.nextInt();

            if (i == 1) {

                if (!FightSys.getSwitch()) {
                    System.out.println("抢票系统未开启");
                } else {
                    if (FightSys.fight()) {
                        System.out.println("抢票成功");
                    } else {
                        System.out.println("抢票失败");
                    }
                }
            }
        }
    }


}
