package com.github.springbootmonitor.job.processor;

import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.WafItemDO;
import com.github.springbootmonitor.pojo.XlsDO;
import com.github.springbootmonitor.service.IHostSourceService;
import com.github.springbootmonitor.service.IWafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 11:01
 */
@Slf4j
@StepScope
@Component(value = "WafProcessor")
public class WafProcessor implements ItemProcessor<XlsDO, WafItemDO> {
    @Resource
    private IWafService service;

    @Override
    public WafItemDO process(@NonNull XlsDO item) {
        return service.insert2Waf(item);
    }

}
