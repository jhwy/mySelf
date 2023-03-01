package com.cxl.myproject.scheduler.quartz;

import com.google.common.collect.Sets;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class QuartzBatchSchedulerDemo {

    public static void main(String[] args) {
        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myjob", "group1").build();
            JobDetail jobDetail2 = JobBuilder.newJob(MyJob2.class).withIdentity("myjob2", "group1").build();
            JobDetail jobDetail3 = JobBuilder.newJob(MyJob3.class).withIdentity("myjob3", "group1").build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .withRepeatCount(10))//执行10次
                    .build();
            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("myTrigger2", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .withRepeatCount(10))//执行10次
                    .build();
            Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("myTrigger3", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(1)//每隔1s执行一次
                    .withRepeatCount(10))//执行10次
                    .build();

            Map triggersAndJobs = new HashMap<>();
            triggersAndJobs.put(jobDetail, Sets.newHashSet(trigger));
            triggersAndJobs.put(jobDetail2,Sets.newHashSet(trigger2));
            triggersAndJobs.put(jobDetail3,Sets.newHashSet(trigger3));

            scheduler.scheduleJobs(triggersAndJobs,true);
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
