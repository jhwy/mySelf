package com.cxl.myproject.scheduler.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        long count2 =jobDataMap.getLong("count");
        System.out.println("myjob run count:"+count2);
        System.out.println("myjob execute, run time :"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        jobDataMap.put("count", ++count2);
    }
}
