package com.test.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by zhangfan on 2015/6/17.
 */
public class JobListenerTest implements JobListener{
    @Override
    public String getName() {
        return "zhangfanListtener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

        System.out.println("job to be execute");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

        System.out.println("job execution vetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

        System.out.println("job was executed");
    }
}
