package com.test.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * Created by zhangfan on 2015/6/17.
 */
public class ConTriggerTest {

    public static void main(String[] args) throws Exception{

        Scheduler schedule = StdSchedulerFactory.getDefaultScheduler();

        JobListenerTest jobListenerTest = new JobListenerTest();


        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myHelloJob")
                .build();

    /*    JobDetail job1 = newJob(HelloJob.class)
                .withIdentity("my")
                .build();
*/

        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0/5 0-30 16 * Jun ?"))
                .forJob("myHelloJob")
                .build();

        Trigger trigger1 = newTrigger()
                .withIdentity("trigger4", "group1")
                .withSchedule(cronSchedule("0/5 0-30 16 * Jun ?"))
                .forJob("myHelloJob")
                .build();


        Set<Trigger> triggerSet = new HashSet<>();
        triggerSet.add(trigger);
        triggerSet.add(trigger1);

        schedule.scheduleJob(job, triggerSet, true);
//        schedule.scheduleJob(job, trigger1);

//        schedule.getListenerManager().addJobListener(jobListenerTest, allJobs());
        schedule.start();
    }
}
