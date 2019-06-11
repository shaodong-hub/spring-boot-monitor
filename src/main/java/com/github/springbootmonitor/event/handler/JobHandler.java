package com.github.springbootmonitor.event.handler;

import com.github.springbootmonitor.event.JobEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 17:05 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component
public class JobHandler {

    @Resource
    private Job job;

    @Resource
    private JobLauncher launcher;

    @Async
    @EventListener
    @SneakyThrows({
            JobRestartException.class,
            JobParametersInvalidException.class,
            JobExecutionAlreadyRunningException.class,
            JobInstanceAlreadyCompleteException.class
    })
    public void listener(JobEvent event) {
        String collection = event.getCollection();
        JobParameters jobParameters = new JobParametersBuilder().addString("collection", collection).toJobParameters();
        launcher.run(job, jobParameters);
    }

}
