package com.performance.interceptor;

import com.performance.utils.adbtools.DeviceConnectManage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-09-17 17:54
 * @Version 1.0
 **/
@Aspect
@Configuration
public class AdbAspect {

    private final static Logger logger = LoggerFactory.getLogger(AdbAspect.class);

    @Around("execution(* com.performance.utils.adbtools.DeviceInfomation.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        try {
            long startTime1 = System.currentTimeMillis();
            Object val = joinPoint.proceed();

            logger.info(" ================> Killing adb service starts. <================ ");
            DeviceConnectManage.getKillServer();
            logger.info(" ================> Killing adb service ends, Time used {} ms.", System.currentTimeMillis() - startTime1);


            long startTime2 = System.currentTimeMillis();
            logger.info(" ================> Start the adb service. <================ ");
            DeviceConnectManage.getStartServer();
            logger.info(" ================> Start adb service end, Time used {} ms.", System.currentTimeMillis() - startTime2);

            return val;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
