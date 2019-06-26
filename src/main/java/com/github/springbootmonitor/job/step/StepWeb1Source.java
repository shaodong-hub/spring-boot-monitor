package com.github.springbootmonitor.job.step;

import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 15:07 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component
public class StepWeb1Source {

    @Resource(name = "ItemReader1GridFs")
    private ItemReader<CsvItemDO> reader;

    @Resource(name = "ItemWriterMongo")
    private ItemWriter<MongoItemDO> writer;

    @Resource(name = "ItemProcessor1Source")
    private ItemProcessor<CsvItemDO, MongoItemDO> processor;

    @Resource(name = "SkipListenerMongo")
    private SkipListener<CsvItemDO, MongoItemDO> skipListener;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "StepWeb1")
    private Step launcherJobStep1() {
        return stepBuilderFactory.get("launcherJobStep1")
                .<CsvItemDO, MongoItemDO>chunk(5)
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
                .listener(skipListener)
                .build();
    }


}
