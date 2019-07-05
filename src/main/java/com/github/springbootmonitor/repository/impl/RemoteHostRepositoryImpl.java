package com.github.springbootmonitor.repository.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.springbootmonitor.common.AttackConsts;
import com.github.springbootmonitor.common.BaseRestTemplate;
import com.github.springbootmonitor.common.MD5Utils;
import com.github.springbootmonitor.pojo.*;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private RestTemplateProperties properties;

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
                .html(result)
                .build();
    }

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
    public ResponseRemoteDO getAttackResultByProxy(HostDnsMappingDO mappingDO) {
        log.info("正在访问远程注解:{}", mappingDO.toString());
        try {
            String result = baseRestTemplate.getRestTemplate(mappingDO).getForObject(mappingDO.getUrl()+AttackConsts.URL_ATTACK, String.class);
            String md5 = MD5Utils.getMD5String(result);
            String title = StringUtils.substringBetween(result, "<title>", "</title>");
            log.info("获取远程 WEB MD5:{}---{}", mappingDO.getHost(), md5);
            return ResponseRemoteDO.builder()
                    .host(mappingDO.getHost())
                    .ip(mappingDO.getIp())
                    .proxy(mappingDO.getProxy().get(0))
                    .http(mappingDO.getHttp())
                    .title(title)
                    .access(Boolean.FALSE)
                    .html(result)
                    .md5(MD5Utils.getMD5String(result))
                    .build();
        }catch(RestClientResponseException ex){
            log.info("{}",ex.getRawStatusCode());
            return ResponseRemoteDO.builder()
                    .host(mappingDO.getHost())
                    .ip(mappingDO.getIp())
                    .proxy(mappingDO.getProxy().get(0))
                    .http(mappingDO.getHttp())
                    .access(Boolean.TRUE)
                    .build();
        }
    }

    @Override
    public WafResponse importHosts2Waf(XlsDO xlsDO){
        log.info("待添加至Waf平台的域名:{}", xlsDO.toString());
        try {
            WafRequest param = new WafRequest(xlsDO);
            param.setAccount(properties.defaultAccount);
            param.setProperty(properties.defaultProperty);
            param.setAreaFlag(properties.areaFlag);
            HttpEntity<WafRequest> entity = new HttpEntity<>( param, organizeHeaders());
            ResponseEntity<String> responseEntity =  new RestTemplate().postForEntity(properties.addPath, entity, String.class);
            WafResponse response = JSONArray.parseObject(responseEntity.getBody(), WafResponse.class);
            log.info(response!=null?response.toString():"");
            return response;
        }catch(RestClientResponseException ex){
            log.info("{}",ex.getRawStatusCode());
            return WafResponse
                    .builder()
                    .resultCode(400)
                    .message("请求过程出错")
                    .build();
        }catch(Exception e){
            log.info("error");
            return WafResponse
                    .builder()
                    .resultCode(500)
                    .message("添加过程中出错")
                    .build();
        }
    }

    @Override
    public WafResponse deleteHostFromWaf(XlsDO xlsDO) {
        log.info("待从Waf平台删除的域名信息:{}", xlsDO.toString());
        try {
            RestTemplate template = new RestTemplate();
            // 先根据查询接口获取到域名所对应的id信息
            WafHostVO hostVO = getHostInfo(template, xlsDO.getHost(), properties.defaultOrgCode);
            if(null == hostVO){
                return WafResponse
                        .builder()
                        .resultCode(200)
                        .message("域名不存在")
                        .build();
            }
            HttpEntity<WafHostVO> entity = new HttpEntity<>( hostVO, organizeHeaders());
            ResponseEntity<String> responseEntity =  template.postForEntity(properties.delPath, entity, String.class);
            WafResponse response = JSONArray.parseObject(responseEntity.getBody(), WafResponse.class);
            log.info(response!=null ? response.toString() : "");
            return response;
        }catch(RestClientResponseException ex){
            log.info("{}",ex.getRawStatusCode());
            return WafResponse
                    .builder()
                    .resultCode(400)
                    .message("请求过程出错")
                    .build();
        }catch(Exception e){
            log.info("error");
            return WafResponse
                    .builder()
                    .resultCode(500)
                    .message("删除过程中出错")
                    .build();
        }
    }

    private WafHostVO getHostInfo(RestTemplate template, String host, String orgCode){

        WafKeyWordVO vo = WafKeyWordVO.builder()
                .domainKey(host)
                .orgCode(orgCode)
                .build();
        HttpEntity<WafKeyWordVO> entity = new HttpEntity<>( vo, organizeHeaders());
        ResponseEntity<String> responseEntity =  template.postForEntity(properties.queryPath, entity, String.class);
        WafResponse response = JSONArray.parseObject(responseEntity.getBody(), WafResponse.class);
        if (response== null || response.getResultCode()!=0){
            return null;
        }
        try{
            JSONObject jsonObject = JSON.parseObject(response.getResult());
            String domainList = jsonObject.get("domainList").toString();
            if(domainList == null || domainList.length()==0){
                return null;
            }
            List<WafHostVO> list = JSONArray.parseArray(domainList, WafHostVO.class);
            if(list!=null && list.size()>0){
                return list.stream().filter(item -> host.equals(item.getDomain())).collect(Collectors.toList()).get(0);
            }
            return null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 设置请求头信息
     * @return HttpHeader
     */
    private HttpHeaders organizeHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        List<String> cookies = new ArrayList<>();
        cookies.add(properties.session);
        cookies.add(properties.zgDid);
        cookies.add(properties.zg);
        headers.put(HttpHeaders.COOKIE,cookies);
        headers.put("X-CSRF-TOKEN", Collections.singletonList(properties.csrfToken));
        return headers;
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
