package com.github.springbootmonitor.job.reader;

import com.github.springbootmonitor.pojo.XlsDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 10:54
 */
@Slf4j
@Component
public class HostReader {
    @Bean(name = "HostReader")
    @StepScope
    public ItemStreamReader<XlsDO> reader(@Value("#{jobParameters['collection']}") String collection, GridFsOperations operations) {
        log.info("reader");
        FlatFileItemReader<XlsDO> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(operations.getResource(collection));
        // 跳过表头
        itemReader.setLinesToSkip(1);
        DefaultLineMapper<XlsDO> lineMapper = getDefaultLineMapper();
        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    private DefaultLineMapper<XlsDO> getDefaultLineMapper() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] array = Arrays.stream(XlsDO.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        tokenizer.setNames(array);
        DefaultLineMapper<XlsDO> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet ->
                XlsDO.builder()
                        .host(fieldSet.readString("host"))
                        .ip(fieldSet.readString("ip"))
                        .build()
        );
        lineMapper.afterPropertiesSet();
        return lineMapper;
    }
}
