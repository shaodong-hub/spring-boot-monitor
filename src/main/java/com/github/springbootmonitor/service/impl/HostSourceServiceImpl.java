package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.common.InetAddressUtils;
import com.github.springbootmonitor.pojo.CsvItemDO;
import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IHostSourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:24 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Service
public class HostSourceServiceImpl implements IHostSourceService {

    @Resource
    private IRemoteHostRepository repository;

    @Override
    public MongoItemDO getRemoteInfoBySource(CsvItemDO itemDO) {
        HostDnsMappingDO mappingDO = getHostDnsMappingDO(itemDO);
        ResponseRemoteDO remoteDO = repository.getRemoteHostByProxy(mappingDO);
        if (remoteDO.getAccess()) {
            Map<String, String> md5map = Collections.singletonMap("source", remoteDO.getMd5());
            Map<String, String> htmlmap = Collections.singletonMap("source", remoteDO.getHtml());
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(remoteDO.getProxy())
                    .ipCdn(itemDO.getIpCdn())
                    .ipWaf(itemDO.getIpWaf())
                    .title(remoteDO.getTitle())
                    .md5(md5map)
                    .html(htmlmap)
                    .http(itemDO.getHttp())
                    .accessSource(remoteDO.getAccess())
//                    // 暂时先绕过cdn访问
//                    .accessCdn(Boolean.TRUE)
                    .desc(remoteDO.getDesc())
                    .build();
        } else {
            return MongoItemDO.builder()
                    .host(remoteDO.getHost())
                    .ipSource(itemDO.getIpSource())
                    .ipCdn(itemDO.getIpCdn())
                    .ipWaf(itemDO.getIpWaf())
                    .http(itemDO.getHttp())
                    .accessSource(Boolean.FALSE)
                    .desc(itemDO.getDesc())
                    .build();
        }
    }

    private static HostDnsMappingDO getHostDnsMappingDO(CsvItemDO itemDO) {
        HostDnsMappingDO mappingDO = new HostDnsMappingDO();
        String host = itemDO.getHost();
        String ipSource = StringUtils.isNotBlank(itemDO.getIpSource()) ? itemDO.getIpSource() : InetAddressUtils.getIP(host);
        mappingDO.setHost(host);
        mappingDO.setIp(ipSource);
        mappingDO.setProxy(Collections.singletonList(ipSource));
        mappingDO.setHttp(itemDO.getHttp());
        return mappingDO;
    }

}
