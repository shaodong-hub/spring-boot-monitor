package com.github.springbootmonitor.job.writer;

import com.github.springbootmonitor.pojo.MongoItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 创建时间为 16:53 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component
public class ItemWriterMongo {

    @Bean("ItemWriterMongo")
    @StepScope
    public MongoItemWriter<MongoItemDO> writer(@Value("#{jobParameters['collection']}") String collection, MongoTemplate mongoTemplate) {
        System.out.println("ItemWriter:" + collection);
        MongoItemWriter<MongoItemDO> itemWriter = new MongoItemWriter<>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection(collection);
        return itemWriter;
    }

}
