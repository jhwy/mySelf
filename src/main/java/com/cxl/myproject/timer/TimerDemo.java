package com.cxl.myproject.timer;

import com.alibaba.fastjson.JSON;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by cxl on 2023/2/17.
 */
public class TimerDemo {

    private static final Logger logger = LoggerFactory.getLogger(TimerDemo.class);

    public static void main(String[] args) {
        System.out.println("hello world");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(new DefaultThreadFactory("my_pool"),1, TimeUnit.MILLISECONDS,4);
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("this is task run timeout:"+ JSON.toJSONString(timeout));
                System.out.println("this is task run thread:"+Thread.currentThread().getName()+",current time:"+System.currentTimeMillis()/1000);
                hashedWheelTimer.newTimeout(this,5000,TimeUnit.MILLISECONDS);
                Thread.sleep(4000);
            }
        };
        hashedWheelTimer.newTimeout(task,0,TimeUnit.MILLISECONDS);
        System.out.println("main thread:"+Thread.currentThread().getName());
    }

}
