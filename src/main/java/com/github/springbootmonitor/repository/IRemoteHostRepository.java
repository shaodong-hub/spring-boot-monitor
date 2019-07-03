package com.github.springbootmonitor.repository;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.pojo.WafResponse;
import com.github.springbootmonitor.pojo.XlsDO;

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

    /**
     * 模拟代理方式拦截远程攻击
     * @param mappingDO 传入参数
     * @return ResponseRemoteDO
     */
    ResponseRemoteDO getAttackResultByProxy(HostDnsMappingDO mappingDO);

    /**
     * 往waf平台添加管理的域名
     * @param xlsDO 域名信息
     * @return WafResponse
     */
    WafResponse importHosts2Waf(XlsDO xlsDO);

    /**
     * 从waf平台删除配置的域名
     * @param xlsDO 域名信息
     * @return WafResponse
     */
    WafResponse deleteHostFromWaf(XlsDO xlsDO);
}
