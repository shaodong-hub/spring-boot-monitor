package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;

/**
 * <p>
 * 创建时间为 15:13 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IHostSourceService {

    MongoItemDO getRemoteInfo(CsvItemDO itemDO);

}
