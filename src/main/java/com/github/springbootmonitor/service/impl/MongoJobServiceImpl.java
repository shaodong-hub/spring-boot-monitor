package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IMongoJobService;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 10:05 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class MongoJobServiceImpl implements IMongoJobService {

    @Override
    public ResultDO<String> runJob(String collection) {
        run(collection, job);
        return ResultDO.<String>builder().status(0).message(ResultDO.StatusEnum.SUCCESS.toString()).build();
    }

    @Override
    public ResultDO<String> runWafJob(String collection) {
        run(collection, wafJob);
        return ResultDO.<String>builder().status(0).message(ResultDO.StatusEnum.SUCCESS.toString()).build();
    }

    @Override
    public ResultDO<String> runWafDelJob(String collection) {
        run(collection, wafDelJob);
        return ResultDO.<String>builder().status(0).message(ResultDO.StatusEnum.SUCCESS.toString()).build();
    }

    @Resource(name = "job")
    private Job job;

    @Resource(name = "addHostJob")
    private Job wafJob;

    @Resource(name = "delHostJob")
    private Job wafDelJob;

    @Resource
    private JobLauncher launcher;

    @Async
    @SneakyThrows({
            JobRestartException.class,
            JobParametersInvalidException.class,
            JobExecutionAlreadyRunningException.class,
            JobInstanceAlreadyCompleteException.class
    })
    public void run(String collection, Job job) {
        JobParameters jobParameters = new JobParametersBuilder().addString("collection", collection).toJobParameters();
        launcher.run(job, jobParameters);
    }

}
