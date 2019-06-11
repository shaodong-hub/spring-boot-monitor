//package com.github.springbootmonitor.job.step;
//
//import com.github.springbootmonitor.pojo.MongoItemDO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.SkipListener;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.BeanUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * <p>
// * 创建时间为 15:07 2019-06-05
// * 项目名称 spring-boot-monitor
// * </p>
// *
// * @author 石少东
// * @version 0.0.1
// * @since 0.0.1
// */
//
//@Slf4j
//@Component
//public class StepWeb4Attack {
//
//    @Resource(name = "ItemReaderMongo")
//    private ItemReader<MongoItemDO> reader;
//
//    @Resource
//    private ItemWriter<MongoItemDO> writer;
//
//    @Resource
//    private ListItemProcessor processor;
//
//    @Resource
//    private MongoSkipListener skipListener;
//
//    @Resource
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean("StepWeb4")
//    private Step launcherJobStep4() {
//        return stepBuilderFactory.get("StepWeb4Attack")
//                .<MongoItemDO, MongoItemDO>chunk(5)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .faultTolerant()
//                .skip(RuntimeException.class)
//                .skipLimit(10)
//                .listener(skipListener)
//                .build();
//    }
//
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
//    @Component
//    @StepScope
//    public static class MongoSkipListener implements SkipListener<MongoItemDO, MongoItemDO> {
//        @Override
//        public void onSkipInRead(Throwable t) {
//
//        }
//
//        @Override
//        public void onSkipInWrite(MongoItemDO item, Throwable t) {
//
//        }
//
//        @Override
//        public void onSkipInProcess(MongoItemDO item, Throwable t) {
//            System.out.println(item + " got exception: " + t);
//        }
//    }
//
//
//}
