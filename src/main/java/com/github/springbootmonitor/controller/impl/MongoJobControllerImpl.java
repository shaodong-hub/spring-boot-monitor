package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.controller.IMongoJobController;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IMongoJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 17:17 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@RestController
public class MongoJobControllerImpl implements IMongoJobController {

    @Resource
    private IMongoJobService service;

    @Override
    @GetMapping("/job/{collection}")
    public ResultDO<String> runJob(@PathVariable("collection") String collection) {
        return service.runJob(collection);
    }

}
