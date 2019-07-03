package com.github.springbootmonitor.controller;

import com.github.springbootmonitor.pojo.ResultDO;

/**
 * <p>
 * 创建时间为 17:16 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IMongoJobController {

    /**
     * 运行任务
     * waf可访问性监测任务
     * @param collection 集合的名称
     * @return ResultDO
     */
    ResultDO<String> runJob(String collection);

    /**
     * 运行任务
     * 向waf平台批量添加域名配置
     * @param collection 文件名
     * @return ResultDO
     */
    ResultDO<String> runWafJob(String collection);

    /**
     * 运行任务
     * 从waf平台批量删除域名配置
     * @param collection 文件名
     * @return ResultDO
     */
    ResultDO<String> delHostJob(String collection);

}
