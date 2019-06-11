package com.github.springbootmonitor.job.reader;

import com.github.springbootmonitor.common.FilesUtils;
import com.github.springbootmonitor.pojo.CsvItemDO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
public class ItemReaderList {

    @Primary
    @Bean("ItemReaderList")
    @StepScope
    @SneakyThrows(IOException.class)
    public ItemReader<CsvItemDO> reader(@Value("#{jobParameters['collection']}") String collection, GridFsOperations operations) {
        InputStream inputStream = operations.getResource(collection).getInputStream();
        List<String> list = FilesUtils.readAllLines(inputStream);
        List<CsvItemDO> csvItemDOS = new ArrayList<>();
        list.forEach(one -> csvItemDOS.add(CsvItemDO.builder()
                .host(one.trim())
                .http(true)
                .desc("")
                .build()));
        return new ListItemReader<>(csvItemDOS);
    }

}
