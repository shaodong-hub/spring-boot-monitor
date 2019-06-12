package com.github.springbootmonitor.pojo;


import com.alibaba.fastjson.JSON;
import com.github.springbootmonitor.common.UrlUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 创建时间为 17:28 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */


@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class HostDnsMappingDO {

    /**
     * 域名
     */
    @Getter
    private String host;

    @Getter
    private String ip;

    private Boolean http;

    /**
     * 域名对应的 IP
     */
    @Getter
    private List<String> proxy;

    public String getUrl() {
        return UrlUtils.joining(host, http);
    }

    public Boolean getHttp(){
        return null == http ? true : http;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
