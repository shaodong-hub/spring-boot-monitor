package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IHostWafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @Author: Du Jiahao
 */
@Slf4j
@Service
public class HostWafServiceImpl implements IHostWafService {

    @Resource
    private IRemoteHostRepository repository;

    @Override
    public MongoItemDO getRemoteInfoByWaf(MongoItemDO itemDO) {
        HostDnsMappingDO mappingDO = getHostDnsMappingDO(itemDO);
        ResponseRemoteDO remoteDO = repository.getRemoteHostByProxy(mappingDO);
        if (remoteDO.getAccess()) {
            Map<String, String> md5map = Collections.singletonMap("waf", remoteDO.getMd5());
            Map<String, String> htmlmap = Collections.singletonMap("waf", remoteDO.getHtml());
            itemDO.setAccessWaf(remoteDO.getAccess());
            itemDO.getMd5().putAll(md5map);
            itemDO.getHtml().putAll(htmlmap);
        } else {
            itemDO.setAccessWaf(Boolean.FALSE);
        }
        return itemDO;
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
