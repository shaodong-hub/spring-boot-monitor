package com.github.springbootmonitor.common.impl;

import com.github.springbootmonitor.common.BaseRestTemplate;
import com.github.springbootmonitor.common.HttpCommonUtils;
import com.github.springbootmonitor.common.InetAddressUtils;
import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.InMemoryDnsResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 创建时间为 17:29 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Primary
@Component
public class RestTemplateHttpClientImpl extends BaseRestTemplate {

    @Override
    public RestTemplate getRestTemplate() {
        return getRestTemplate(Collections.emptyList());
    }

    @Override
    public RestTemplate getRestTemplate(HostDnsMappingDO mapping) {
        return getRestTemplate(Collections.singleton(mapping));
    }

    @Override
    public RestTemplate getRestTemplate(Collection<HostDnsMappingDO> collection) {
        RestTemplate restTemplate = new RestTemplate(HttpCommonUtils.getHttpMessageConverters());
        restTemplate.setRequestFactory(getClientHttpRequestFactory(collection));
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    /**
     * DnsResolver 解析器
     *
     * @param collection 集合
     * @return DnsResolver
     */
    private DnsResolver getDnsResolver(Collection<HostDnsMappingDO> collection) {
        final InMemoryDnsResolver dnsResolver = new InMemoryDnsResolver();
        collection.forEach(one -> {
            Collection<String> ipSet = one.getProxy();
            InetAddress[] addresses = ipSet.stream()
                    .filter(StringUtils::isNotBlank)
                    .map(InetAddressUtils::getInetAddress)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toArray(InetAddress[]::new);
            dnsResolver.add(one.getHost(), addresses);
        });
        return dnsResolver;
    }

    /**
     * 获取连接工厂
     *
     * @param collection Collection
     * @return ClientHttpRequestFactory
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory(Collection<HostDnsMappingDO> collection) {
        // 长连接保持 99 天
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(
                getDefaultRegistry(), null, null, getDnsResolver(collection), 1, TimeUnit.MINUTES);
        // 总连接数
        poolingConnectionManager.setMaxTotal(10);
        // 同路由的并发数
        poolingConnectionManager.setDefaultMaxPerRoute(10);
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(poolingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(10, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        // 获取请求头
        List<Header> headers = HttpCommonUtils.getHeaders();
        httpClientBuilder.setDefaultHeaders(headers);
        HttpClient httpClient = httpClientBuilder.build();
        // httpClient连接配置，底层是配置RequestConfig
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    /**
     * 获取默认 Registry
     *
     * @return Registry
     */
    private Registry<ConnectionSocketFactory> getDefaultRegistry() {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
    }

}
