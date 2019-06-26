package com.github.springbootmonitor.job.step;

import com.github.springbootmonitor.pojo.MongoItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * <p>
 * 创建时间为 15:07 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Component
public class StepWeb3Waf {

    @Resource(name = "ItemReader3Mongo")
    private ItemReader<MongoItemDO> reader;

    @Resource(name = "ItemWriterMongo")
    private ItemWriter<MongoItemDO> writer;

    @Resource(name = "ItemProcessor3Waf")
    private ItemProcessor<MongoItemDO, MongoItemDO> processor;

    @Resource
    private MongoSkipListener skipListener;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "StepWeb3")
    private Step launcherJobStep3() {
        return stepBuilderFactory.get("launcherJobStep3")
                .<MongoItemDO, MongoItemDO>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(RuntimeException.class)
                .skipLimit(10)
                .listener(skipListener)
                .build();
    }


//
//    @Component
//    @StepScope
//    public static class ListItemProcessor implements ItemProcessor<MongoItemDO, MongoItemDO> {
//        @Override
//        public MongoItemDO process(@NonNull MongoItemDO item) {
//            // TODO 访问源站能否访问 记录访问状态 源站IP 源站title
//            System.out.println(item);
//            MongoItemDO temp = new MongoItemDO();
//            BeanUtils.copyProperties(item, temp);
//            temp.setData(String.valueOf(System.currentTimeMillis()));
//            return temp;
//        }
//    }
//
//

    @Component
    @StepScope
    public static class MongoSkipListener implements SkipListener<MongoItemDO, MongoItemDO> {
        @Override
        public void onSkipInRead(Throwable t) {

        }

        @Override
        public void onSkipInWrite(MongoItemDO item, Throwable t) {
            System.out.println(item + " got exception in write option: " + t);
        }

        @Override
        public void onSkipInProcess(MongoItemDO item, Throwable t) {
            System.out.println(item + " got exception in process option: " + t);
        }
    }


}
