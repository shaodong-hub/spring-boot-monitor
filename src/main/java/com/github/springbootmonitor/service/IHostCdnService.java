package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.MongoItemDO;

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

public interface IHostCdnService {

    /**
     * 通过CDN方式获取主机信息
     *
     * @param itemDO MongoItemDO
     * @return MongoItemDO
     */
    MongoItemDO getRemoteInfoByCdn(MongoItemDO itemDO);

}
