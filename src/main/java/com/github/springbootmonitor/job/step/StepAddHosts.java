package com.github.springbootmonitor.job.step;

import com.github.springbootmonitor.pojo.WafItemDO;
import com.github.springbootmonitor.pojo.XlsDO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 10:45
 */
@Component
public class StepAddHosts {

    @Resource(name = "HostReader")
    private ItemReader<XlsDO> reader;

    @Resource(name = "WafProcessor")
    private ItemProcessor<XlsDO, WafItemDO> processor;

    @Resource(name = "AddHostWriter")
    private ItemWriter<WafItemDO> writer;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "AddHostStep")
    private Step addHostJobStep() {
        return stepBuilderFactory.get("addHostJobStep")
                .<XlsDO, WafItemDO>chunk(5)
                .reader(reader)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .processor(processor)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .build();
    }

}
