package com.github.springbootmonitor.job.reader;

import com.github.springbootmonitor.pojo.CsvItemDO;
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
public class ItemReader1GridFs {

    @Bean("ItemReader1GridFs")
    @StepScope
    public ItemStreamReader<CsvItemDO> reader(@Value("#{jobParameters['collection']}") String collection, GridFsOperations operations) {
        FlatFileItemReader<CsvItemDO> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(operations.getResource(collection));
        itemReader.setLinesToSkip(1);
        DefaultLineMapper<CsvItemDO> lineMapper = getDefaultLineMapper();
        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    private DefaultLineMapper<CsvItemDO> getDefaultLineMapper() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] array = Arrays.stream(CsvItemDO.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        tokenizer.setNames(array);
        DefaultLineMapper<CsvItemDO> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet ->
                CsvItemDO.builder()
                        .host(fieldSet.readString("host"))
                        .ipSource(fieldSet.readString("ipSource"))
                        .ipCdn(fieldSet.readString("ipCdn"))
                        .ipWaf(fieldSet.readString("ipWaf"))
                        .http(fieldSet.readBoolean("http"))
                        .desc(fieldSet.readString("desc"))
                        .build()
        );
        lineMapper.afterPropertiesSet();
        return lineMapper;
    }

}
