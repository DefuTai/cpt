package com.performance.annotation;

import java.lang.annotation.*;

/**
 * Controller日志拦截器
 *
 * @Author 鲢鱼
 * @Data 2019-09-16 11:41
 * @Version 1.0
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

    String description() default "";

}
