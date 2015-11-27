package com.test.fighticket;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zhangfan on 2015/6/18.
 */
public class FightTimeTaskStartSys implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        FightSys.setTicketCount(15);
        System.out.println("数据初始化");


        if (!FightSys.startSys()) {
            FightSys.startSys();
        }
        System.out.println("开启抢票系统");
    }

    public static void execute()  throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = newJob(FightTimeTaskStartSys.class)
                .withIdentity("startFightSys")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("startTrigger")
                .withSchedule(cronSchedule("0 29 17 18 Jun ?"))
                .forJob("startFightSys")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

    }
}
