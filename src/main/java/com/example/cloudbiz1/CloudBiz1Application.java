package com.example.cloudbiz1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@MapperScan(basePackages = "com.example.cloudbiz1.mapper")
public class CloudBiz1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudBiz1Application.class, args);
    }

}
