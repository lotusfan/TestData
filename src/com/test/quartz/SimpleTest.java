package com.test.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zhangfan on 2015/6/17.
 */
public class SimpleTest {

    public static int count = 0;

    public static void main(String[] args) throws Exception{
        // define the job and tie it to our HelloJob class

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        scheduler.start();

        while (!scheduler.isShutdown()) {
            Thread.sleep(1000);
            if (count > 5){
                System.out.println(count);
                scheduler.shutdown();
                System.out.println(scheduler.isShutdown());
            }
        }

    }

}


