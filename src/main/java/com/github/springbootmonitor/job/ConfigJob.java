package com.github.springbootmonitor.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 15:06 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
public class ConfigJob {

    @Resource(name = "StepWeb1")
    private Step step1;

    @Resource(name = "StepWeb2")
    private Step step2;

    @Resource(name = "StepWeb3")
    private Step step3;

    @Resource(name = "StepWeb4")
    private Step step4;

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Bean(name = "job")
    public Job launcherJob() {
        return jobBuilderFactory.get("launcherJob")
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .build();
    }



}
