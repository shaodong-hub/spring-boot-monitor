package com.github.springbootmonitor.common;

import com.github.springbootmonitor.pojo.HostDnsMappingDO;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * <p>
 * 创建时间为 17:27 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public abstract class BaseRestTemplate {

    /**
     * @return RestTemplate 获取默认 RestTemplate
     */
    public abstract RestTemplate getRestTemplate();

    public abstract RestTemplate getRestTemplate(HostDnsMappingDO mappingDO);

    public abstract RestTemplate getRestTemplate(Collection<HostDnsMappingDO> collection);

}
