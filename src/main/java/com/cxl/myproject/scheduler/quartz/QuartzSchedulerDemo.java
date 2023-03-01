package com.cxl.myproject.scheduler.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.Key;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class QuartzSchedulerDemo {

    public static void main(String[] args) {
        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myjob", "group1").build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
                    .startAt(new Date(1677640380000L))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .withRepeatCount(10))//执行10次
                    .build();
            scheduler.scheduleJob(jobDetail,trigger);
            System.out.println("=========scheduler start ============="+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            scheduler.start();

            TimeUnit.MINUTES.sleep(1);
            System.out.println("=========scheduler shutdown ============="+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            scheduler.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
