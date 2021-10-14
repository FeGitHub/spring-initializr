package com.jeff.initializr.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopToAllController {
    private static Logger logger = LoggerFactory.getLogger(AopToAllController.class);

    @Pointcut("execution(* com.jeff.initializr.controller..*.*(..))")
    private void aopToAll() {

    }

    @Around("aopToAll()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("--interceptor--");
        return pjp.proceed();
    }


}
