package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.service.IHostCdnService;
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
@StepScope
@Component(value = "ItemProcessor2Cdn")
public class ItemProcessor2Cdn implements ItemProcessor<MongoItemDO, MongoItemDO> {

    @Resource
    private IHostCdnService service;

    @Override
    public MongoItemDO process(@NonNull MongoItemDO item){
        return service.getRemoteInfoByCdn(item);
    }

}
