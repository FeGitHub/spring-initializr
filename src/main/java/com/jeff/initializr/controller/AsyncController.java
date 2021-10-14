package com.jeff.initializr.controller;

import com.jeff.initializr.scheduled.ScheduledJob;
import com.jeff.initializr.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;

@Controller
@RequestMapping("async")
public class AsyncController {
    private static Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    AsyncService asyncService;



    @RequestMapping("/test")
    @ResponseBody
    public String testThread() {
        logger.info("testThread--main-start");
        asyncService.testAsync();
        logger.info("testThread--main-end");
        return "执行完成";
    }


    @RequestMapping("/asyncTestReturnFuture")
    @ResponseBody
    public String asyncTestReturnFuture() {
        logger.info("asyncTestReturnFuture--main-start");
        Future<String> future =  asyncService.asyncTestReturnFuture();
        long startProcTime = System.currentTimeMillis();
        while(true) {
            if(future.isDone()) {
                break;
            } else if(System.currentTimeMillis() - startProcTime > 59000) {//59秒内执行
                return "超过时间范围，异步代码继续执行";
            }
        }
        logger.info("asyncTestReturnFuture--main-end");
        return "时间范围内执行完成";
    }

}
