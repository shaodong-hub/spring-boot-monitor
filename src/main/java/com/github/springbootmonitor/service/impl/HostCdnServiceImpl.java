package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IHostCdnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:44 2019-06-11
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Service
public class HostCdnServiceImpl implements IHostCdnService {

    @Resource
    private IRemoteHostRepository repository;

    @Override
    public MongoItemDO getRemoteInfoByCdn(MongoItemDO itemDO) {
        HostDnsMappingDO mappingDO = getHostDnsMappingDO(itemDO);
        ResponseRemoteDO remoteDO = repository.getRemoteHostByProxy(mappingDO);
        if (remoteDO.getAccess()) {
            Map<String, String> md5map = Collections.singletonMap("cdn", remoteDO.getMd5());
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(remoteDO.getProxy())
                    .title(remoteDO.getTitle())
                    .md5(md5map)
                    .http(itemDO.getHttp())
                    .accessSource(remoteDO.getAccess())
                    .desc(remoteDO.getDesc())
                    .build();
        } else {
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(itemDO.getIpSource())
                    .ipCdn(itemDO.getIpCdn())
                    .ipWaf(itemDO.getIpWaf())
                    .accessSource(Boolean.FALSE)
                    .desc(itemDO.getDesc())
                    .build();
        }
    }

    private HostDnsMappingDO getHostDnsMappingDO(MongoItemDO itemDO) {
        return HostDnsMappingDO.builder()
                .host(itemDO.getHost())
                .ip(itemDO.getIpSource())
                .http(itemDO.getHttp())
                .proxy(Collections.singletonList(itemDO.getIpCdn()))
                .build();

    }


}
