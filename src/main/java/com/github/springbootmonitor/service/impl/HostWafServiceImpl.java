package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IHostWafService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @Author: Du Jiahao
 */
@Service
public class HostWafServiceImpl implements IHostWafService {

    @Resource
    private IRemoteHostRepository repository;

    @Override
    public MongoItemDO getRemoteInfoByWaf(MongoItemDO itemDO) {
        HostDnsMappingDO mappingDO = getHostDnsMappingDO(itemDO);
        ResponseRemoteDO remoteDO = repository.getRemoteHostByProxy(mappingDO);
        if (remoteDO.getAccess()) {
            Map<String, String> md5map = Collections.singletonMap("cdn", remoteDO.getMd5());
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(remoteDO.getProxy())
                    .title(remoteDO.getTitle())
                    .ipCdn(itemDO.getIpCdn())
                    .ipWaf(itemDO.getIpWaf())
                    .md5(md5map)
                    .http(itemDO.getHttp())
                    .accessWaf(remoteDO.getAccess())
                    .desc(remoteDO.getDesc())
                    .build();
        } else {
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(itemDO.getIpSource())
                    .ipCdn(itemDO.getIpCdn())
                    .ipWaf(itemDO.getIpWaf())
                    .accessWaf(Boolean.FALSE)
                    .desc(itemDO.getDesc())
                    .build();
        }
    }

    private HostDnsMappingDO getHostDnsMappingDO(MongoItemDO itemDO) {
        return HostDnsMappingDO.builder()
                .host(itemDO.getHost())
                .ip(itemDO.getIpSource())
                .http(itemDO.getHttp())
                .proxy(Collections.singletonList(itemDO.getIpWaf()))
                .build();

    }
}
