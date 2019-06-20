package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.service.IHostAttackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/19 0019 11:13
 */
@Slf4j
@StepScope
@Component(value = "ItemProcessor4Attack")
public class ItemProcessor4Attack implements ItemProcessor<MongoItemDO, MongoItemDO> {

    @Resource
    private IHostAttackService service;

    @Override
    public MongoItemDO process(@NonNull MongoItemDO item) {
        return service.getRemoteInfoByAttack(item);
    }
}
