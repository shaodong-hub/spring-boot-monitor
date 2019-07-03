package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.WafItemDO;
import com.github.springbootmonitor.pojo.XlsDO;
import com.github.springbootmonitor.service.IWafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/7/2 0002 17:43
 */
@Slf4j
@StepScope
@Component(value = "DelHostProcessor")
public class DelHostProcessor implements ItemProcessor<XlsDO, WafItemDO> {
    @Resource
    private IWafService service;

    @Override
    public WafItemDO process(@NonNull XlsDO item) {
        return service.deleteFromWaf(item);
    }

}
