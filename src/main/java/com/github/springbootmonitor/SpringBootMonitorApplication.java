package com.github.springbootmonitor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


/**
 * @author shao
 */

@EnableMongoAuditing
@EnableCircuitBreaker
@EnableBatchProcessing
@SpringBootApplication
public class SpringBootMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitorApplication.class, args);
    }

}
