package com.github.springbootmonitor.job.reader;

import com.github.springbootmonitor.pojo.MongoItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * <p>
 * 创建时间为 16:51 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Component
public class ItemReader3Mongo {

    @Bean("ItemReader3Mongo")
    @StepScope
    public ItemReader<MongoItemDO> reader(@Value("#{jobParameters['collection']}") String collection, MongoTemplate mongoTemplate) {
        log.info("ItemReader Collection:" + collection);
        MongoItemReader<MongoItemDO> itemReader = new MongoItemReader<>();
        itemReader.setTemplate(mongoTemplate);
        itemReader.setPageSize(5);
        itemReader.setCollection(collection);
        itemReader.setQuery(new Query(where("access_cdn").is(true)));
        itemReader.setTargetType(MongoItemDO.class);
        return itemReader;
    }

}
