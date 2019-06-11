package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.service.IHostSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 11:00 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component(value = "ItemProcessor1Source")
@StepScope
public class ItemProcessor1Source implements ItemProcessor<CsvItemDO, MongoItemDO> {

    @Resource
    private IHostSourceService service;

    @Override
    public MongoItemDO process(@NonNull CsvItemDO item) {
        return service.getRemoteInfo(item);
    }

}
