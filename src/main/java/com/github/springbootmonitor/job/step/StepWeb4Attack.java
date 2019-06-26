package com.github.springbootmonitor.job.step;

import com.github.springbootmonitor.job.processor.ItemProcessor4Attack;
import com.github.springbootmonitor.pojo.MongoItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
public class StepWeb4Attack {

    @Resource(name = "ItemReader4Mongo")
    private ItemReader<MongoItemDO> reader;

    @Resource(name = "ItemWriterMongo")
    private ItemWriter<MongoItemDO> writer;

    @Resource(name = "ItemProcessor4Attack")
    private ItemProcessor4Attack processor;

//    @Resource
//    private MongoSkipListener skipListener;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "StepWeb4")
    private Step launcherJobStep4() {
        return stepBuilderFactory.get("StepWeb4Attack")
                .<MongoItemDO, MongoItemDO>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(RuntimeException.class)
                .skipLimit(10)
//                .listener(skipListener)
                .build();
    }

//    @Component
//    @StepScope
//    public static class MongoSkipListener implements SkipListener<MongoItemDO, MongoItemDO> {
//        @Override
//        public void onSkipInRead(@NonNull Throwable t) {
//
//        }
//
//        @Override
//        public void onSkipInWrite(@NonNull MongoItemDO item, @NonNull Throwable t) {
//
//        }
//
//        @Override
//        public void onSkipInProcess(@NonNull MongoItemDO item, @NonNull Throwable t) {
//            System.out.println(item + " got exception: " + t);
//        }
//    }


}
