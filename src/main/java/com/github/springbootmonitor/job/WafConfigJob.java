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

    @Resource(name = "WafStep")
    private Step wafStep;

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Bean(name = "WafJob")
    public Job laucherWafJob(){
        return jobBuilderFactory.get("launcherWafJob")
                .start(wafStep)
                .build();
    }
}
