package com.cxl.myproject.scheduler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyJob3 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        long count = (long)jobExecutionContext.getJobDetail().getJobDataMap().get("count");
        System.out.println("myjob3 run count:"+count);
        System.out.println("myjob3 execute, run time :"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        jobExecutionContext.getJobDetail().getJobDataMap().put("count", ++count);

    }
}
