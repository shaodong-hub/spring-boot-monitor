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
 * @Date: 2019/7/2 0002 17:28
 */
@Component
public class StepDelHosts {

    @Resource(name = "HostReader")
    private ItemReader<XlsDO> reader;

    @Resource(name = "DelHostProcessor")
    private ItemProcessor<XlsDO, WafItemDO> processor;

    @Resource(name = "DelHostWriter")
    private ItemWriter<WafItemDO> writer;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "DelHostStep")
    private Step delHostJobStep(){
        return stepBuilderFactory.get("delHostJobStep")
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
