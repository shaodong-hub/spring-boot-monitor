package com.github.springbootmonitor.job.listener;

import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 创建时间为 11:04 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@StepScope
@Component("SkipListenerMongo")
public class SkipListenerMongo implements SkipListener<CsvItemDO, MongoItemDO> {

    @Override
    public void onSkipInRead(Throwable t) {


    }

    @Override
    public void onSkipInWrite(MongoItemDO item, Throwable t) {

    }

    @Override
    public void onSkipInProcess(CsvItemDO item, Throwable t) {

    }
}
