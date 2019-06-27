package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IHostAttackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/19 0019 11:16
 */
@Service
public class HostAttackServiceImpl implements IHostAttackService {

    @Resource
    private IRemoteHostRepository repository;

    @Override
    public MongoItemDO getRemoteInfoByAttack(MongoItemDO itemDO) {
        HostDnsMappingDO mappingDO = getHostDnsMappingDO(itemDO);
        ResponseRemoteDO remoteDO = repository.getAttackResultByProxy(mappingDO);

        if (remoteDO.getAccess()) {
            itemDO.setDefend(Boolean.TRUE);
        } else {
            Map<String, String> map = Collections.singletonMap("attack", remoteDO.getMd5());
            Map<String, String> htmlmap = Collections.singletonMap("attack", remoteDO.getHtml());
            itemDO.getMd5().putAll(map);
            itemDO.getHtml().putAll(htmlmap);
            itemDO.setDefend(Boolean.FALSE);
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
