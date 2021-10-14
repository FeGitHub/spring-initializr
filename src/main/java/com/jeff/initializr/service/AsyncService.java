package com.jeff.initializr.service;

import com.jeff.initializr.scheduled.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {
    private static Logger logger = LoggerFactory.getLogger(ScheduledJob.class);

    @Async
    public void testAsync(){//异步方法使用注解@Async ,返回值为void或者Future
      for(int i=0;i<100;i++){
          logger.info("异步代码执行中...");
      }
    }

    @Async
    public Future<String> asyncTestReturnFuture() {
        for(int i=0;i<100;i++){
            logger.info("异步代码执行中...");
        }
        return new AsyncResult<String>("success");
    }

}
