package com.github.springbootmonitor.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 10:42
 */
@Configuration
public class WafConfigJob {

    @Resource(name = "AddHostStep")
    private Step addHostStep;

    @Resource(name = "DelHostStep")
    private Step delHostStep;

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Bean(name = "addHostJob")
    public Job addHostJob(){
        return jobBuilderFactory.get("addHostJob")
                .start(addHostStep)
                .build();
    }

    @Bean(name = "delHostJob")
    public Job delHostJob(){
        return jobBuilderFactory.get("delHostJob")
                .start(delHostStep)
                .build();
    }
}
