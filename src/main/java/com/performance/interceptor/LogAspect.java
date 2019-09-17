package com.performance.interceptor;

import com.alibaba.fastjson.JSON;
import com.performance.annotation.ControllerLog;
import com.performance.annotation.ServiceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 描述：
 *
 * @Author 鲢鱼
 * @Data 2019-09-17 11:45
 * @Version 1.0
 **/
@Aspect
@Configuration
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private ThreadLocal<Long> time = new ThreadLocal<>();

    @Pointcut("@annotation(com.performance.annotation.ControllerLog)")
    public void controllerPointCut() {
    }

    @Pointcut("@annotation(com.performance.annotation.ServiceLog)")
    public void servicePointCut() {
    }

    @Before("controllerPointCut()")
    public void controllerBefore(JoinPoint joinPoint) {
        try {
            logger.info("Controller方法备注：" + getControllerMethodDescription(joinPoint));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before("servicePointCut()")
    public void serviceBefore(JoinPoint joinPoint) {
        try {
            logger.info("Service方法备注：" + getServiceMthodDescription(joinPoint));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法执行前执行
     *
     * @param joinPoint
     */
    @Before("execution(* com.performance.services.impl.*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        try {
            time.set(System.currentTimeMillis());
            logger.info(" ================> 前置通知开始 <================ ");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("请求IP：" + getRemoteAddr());
            logger.info(" ================> 前置通知结束 <================ ");
        } catch (Exception e) {
            logger.error(" >>>>>>>>>>>>>>>> 前置通知异常：{}", e.getMessage());
        }
    }

    @AfterReturning("execution(* com.performance.services.impl.*.*(..))")
    public void doAfterReturning(JoinPoint joinPoint) {
        logger.info(" ################ 方法执行完毕：{}, Time used {} ms.", joinPoint.getSignature(), System.currentTimeMillis() - time.get());
    }

    /**
     * 异常通知，用于拦截Service层异常日志
     * 注：只有切点向外抛出异常，才会被拦截
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "servicePointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        try {
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
                }
            }
            logger.error(" >>>>>>>>>>>>>>>> 异常通知开始 <<<<<<<<<<<<<<<< ");
            logger.error("方法描述：" + getServiceMthodDescription(joinPoint));
            logger.error("请求IP：" + getRemoteAddr());
            logger.error("异常方法：" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.error("请求参数：" + params);
            logger.error("异常代码：" + ex.getClass().getName());
            logger.error("异常信息：" + ex.getMessage());
            logger.error(" >>>>>>>>>>>>>>>> 异常通知结束 <<<<<<<<<<<<<<<< ");
        } catch (Exception e) {
            logger.error(" >>>>>>>>>>>>>>>> 后置通知异常：{}", e.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息，用于controller层注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息，用于service层注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取客户端IP地址
     *
     * @return
     */
    private String getRemoteAddr() {
        String ip = "";
        ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != servlet) {
            HttpServletRequest request = servlet.getRequest();
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
