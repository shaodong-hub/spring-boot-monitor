package com.github.springbootmonitor.repository.impl;

import com.github.springbootmonitor.common.BaseRestTemplate;
import com.github.springbootmonitor.common.MD5Utils;
import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import com.github.springbootmonitor.pojo.ResponseRemoteDO;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;

/**
 * <p>
 * 创建时间为 17:33 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Repository
public class RemoteHostRepositoryImpl implements IRemoteHostRepository {

    @Resource
    private BaseRestTemplate baseRestTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getRemoteHostByProxyHystrix",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "100"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "100")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "800"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "10"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "10000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "48000")
            }
    )
    public ResponseRemoteDO getRemoteHostByProxy(HostDnsMappingDO mappingDO) {
        log.info("正在访问远程注解:{}", mappingDO.toString());
        String result = baseRestTemplate.getRestTemplate(mappingDO).getForObject(mappingDO.getUrl(), String.class);
        String title = StringUtils.substringBetween(result, "<title>", "</title>");
        String md5 = MD5Utils.getMD5String(result);
        log.info("获取远程 WEB MD5:{}---{}", mappingDO.getHost(), md5);
        return ResponseRemoteDO.builder()
                .host(mappingDO.getHost())
                .ip(mappingDO.getIp())
                .proxy(mappingDO.getProxy().get(0))
                .http(mappingDO.getHttp())
                .title(title)
                .access(Boolean.TRUE)
                .md5(MD5Utils.getMD5String(result))
                .build();
    }

    /**
     * 超时或者错误 默认进入的方法
     *
     * @param mappingDO 请求的URL
     * @return InfoSourceDO
     */
    @SuppressWarnings("unused")
    public ResponseRemoteDO getRemoteHostByProxyHystrix(HostDnsMappingDO mappingDO, Throwable e) {
        if (e.getClass().equals(HttpClientErrorException.MethodNotAllowed.class)) {
            log.info("发生错误进入熔断方法:{}---{}", mappingDO.toString(), "HttpClientErrorException.MethodNotAllowed");
            return ResponseRemoteDO.builder()
                    .host(mappingDO.getHost())
                    .ip(mappingDO.getIp())
                    .status(HttpStatus.OK.value())
                    .access(Boolean.FALSE)
                    .proxy(mappingDO.getProxy().get(0))
                    .status(405)
                    .http(mappingDO.getHttp())
                    .build();
        }
        log.info("发生错误进入熔断方法:{}", mappingDO.toString());
        return ResponseRemoteDO.builder()
                .host(mappingDO.getHost())
                .ip(mappingDO.getIp())
                .access(Boolean.FALSE)
                .http(mappingDO.getHttp())
                .proxy(mappingDO.getProxy().get(0))
                .build();
    }

}
