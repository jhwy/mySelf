package com.cxl.myproject;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by cxl on 2023/2/17.
 */
public class HelloWorld {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    private static MyTestSimpleJob myJob = new MyTestSimpleJob();

    public static void main(String[] args) {
        System.out.println("hello world");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(new DefaultThreadFactory("my_pool"), 1, TimeUnit.MILLISECONDS, 4);
        //ShardingContext shardingContext = new ShardingContext();
        //myJob.execute(shardingContext);
    }

    static class MyTestSimpleJob implements SimpleJob {

        @Override

        public void execute(ShardingContext shardingContext) {

            logger.info(String.format("------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",

                    Thread.currentThread().getId(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem()));

        }

    }

}
