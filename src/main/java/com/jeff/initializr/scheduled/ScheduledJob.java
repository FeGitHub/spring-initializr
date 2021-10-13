package com.jeff.initializr.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ScheduledJob {
    private static Logger logger = LoggerFactory.getLogger(ScheduledJob.class);

    @Scheduled(cron="${custom.scheduled.cron}")
    @PostConstruct //启动项目先执行
    private void process() {
        logger.info("定时任务启动");
    }

}
