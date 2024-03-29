package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.ResultDO;

/**
 * <p>
 * 创建时间为 10:03 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IMongoJobService {

    /**
     * 返回执行状态
     *
     * @param collection 集合名词
     * @return ResultDO
     */
    ResultDO<String> runJob(String collection);

}
