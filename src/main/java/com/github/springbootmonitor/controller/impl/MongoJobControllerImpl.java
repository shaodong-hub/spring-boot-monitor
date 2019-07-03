package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.controller.IMongoJobController;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IMongoJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "启动任务的接口")
public class MongoJobControllerImpl implements IMongoJobController {

    @Resource
    private IMongoJobService service;

    @Override
    @GetMapping("/job/{collection}")
    @ApiOperation(value = "启动单个任务", httpMethod = "GET", notes = "启动单个任务")
    @ApiImplicitParam(name = "collection", value = "传入文件的名称", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "获取为空")
    })
    public ResultDO<String> runJob(@PathVariable("collection") String collection) {
        return service.runJob(collection);
    }

    @Override
    @GetMapping("/waf/job/{collection}")
    @ApiOperation(value = "添加Waf域名任务", httpMethod = "GET", notes = "添加Waf域名任务")
    @ApiImplicitParam(name = "collection", value = "传入文件的名称", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "获取为空")
    })
    public ResultDO<String> runWafJob(@PathVariable("collection") String collection) {
        return service.runWafJob(collection);
    }

    @Override
    @GetMapping("/waf/job/del/{collection}")
    @ApiOperation(value = "批量删除域名配置任务", httpMethod = "GET", notes = "批量删除域名配置")
    @ApiResponses({
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "获取为空")
    })
    @ApiImplicitParam(name = "collection", value = "传入文件的名称", required = true, dataType = "String", paramType = "path")
    public ResultDO<String> delHostJob(@PathVariable("collection") String collection) {
        return service.runWafDelJob(collection);
    }

}
