package com.performance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = MongoAutoConfiguration.class, scanBasePackages = {"com.performance"})
@MapperScan(basePackages = "com.performance.dao")
@SpringBootConfiguration    // 配置控制
@EnableScheduling   // 启用定时任务
@EnableAspectJAutoProxy(proxyTargetClass = true)    // 启用AOP
@EnableTransactionManagement    // 启用事务管理
public class CptApplication {

    public static void main(String[] args) {
        SpringApplication.run(CptApplication.class, args);
    }

}
