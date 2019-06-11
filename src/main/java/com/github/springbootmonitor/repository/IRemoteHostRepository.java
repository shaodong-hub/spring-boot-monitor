package com.github.springbootmonitor.repository;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;

/**
 * <p>
 * 创建时间为 17:32 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IRemoteHostRepository {

    /**
     * 通过代理方式访问远程主机
     *
     * @param hostDnsMappingDO  远程主机域名
     * @return ResponseRemoteDO
     */
    ResponseRemoteDO getRemoteHostByProxy(HostDnsMappingDO hostDnsMappingDO);


}
