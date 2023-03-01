package com.cxl.myproject.scheduler.quartz;

import com.google.common.collect.Sets;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.listeners.JobChainingJobListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class QuartzMultitaskingLinkDemo {

    public static void main(String[] args) {
        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = stdSchedulerFactory.getScheduler();

            JobChainingJobListener listener = new JobChainingJobListener("JobLink");
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myjob", "group1").build();
            JobDetail jobDetail2 = JobBuilder.newJob(MyJob2.class).withIdentity("myjob2", "group1").storeDurably().build();
            JobDetail jobDetail3 = JobBuilder.newJob(MyJob3.class).withIdentity("myjob3", "group1").storeDurably().build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ? "))
                    .build();

            listener.addJobChainLink(jobDetail.getKey(),jobDetail2.getKey());
            listener.addJobChainLink(jobDetail2.getKey(),jobDetail3.getKey());

            scheduler.getListenerManager().addJobListener(listener);

            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.addJob(jobDetail2,true);
            scheduler.addJob(jobDetail3,true);

            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
