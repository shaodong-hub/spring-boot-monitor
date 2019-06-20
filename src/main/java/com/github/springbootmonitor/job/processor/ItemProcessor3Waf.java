package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.service.IHostWafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 */
@Slf4j
@StepScope
@Component(value = "ItemProcessor3Waf")
public class ItemProcessor3Waf implements ItemProcessor<MongoItemDO, MongoItemDO> {

    @Resource
    private IHostWafService service;

    @Override
    public MongoItemDO process(@NonNull MongoItemDO item) {
        return service.getRemoteInfoByWaf(item);
    }

}
