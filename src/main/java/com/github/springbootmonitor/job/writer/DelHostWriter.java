package com.github.springbootmonitor.job.writer;

import com.github.springbootmonitor.pojo.WafItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Du Jiahao
 * @Date: 2019/7/3 0003 9:14
 */
@Slf4j
@Component
public class DelHostWriter {

    @Bean("DelHostWriter")
    @StepScope
    public MongoItemWriter<WafItemDO> delHostWriter(@Value("#{jobParameters['collection']}") String collection, MongoTemplate mongoTemplate) {
        System.out.println("DelHostWriter:" + collection);
        MongoItemWriter<WafItemDO> itemWriter = new MongoItemWriter<>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection("del_"+collection);
        return itemWriter;
    }

}
