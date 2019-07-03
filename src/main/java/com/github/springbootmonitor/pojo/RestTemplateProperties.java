package com.github.springbootmonitor.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/26 0026 9:43
 */
@Data
@Configuration
@PropertySource("classpath:restTemplate.properties")
public class RestTemplateProperties {
    @Value("${waf.http}")
    public String http;
    @Value("${waf.host}")
    public String host;
    @Value("${waf.addPath}")
    public String addPath;
    @Value("${waf.defaultAccount}")
    public String defaultAccount;
    @Value("${waf.defaultProperty}")
    public String defaultProperty;
    @Value("${waf.AreaFlag}")
    public String areaFlag;
    @Value("${waf.delPath}")
    public String delPath;
    @Value("${waf.queryPath}")
    public String queryPath;
    @Value("${waf.defaultOrgCode}")
    public String defaultOrgCode;
    @Value("${waf.session}")
    public String session;
    @Value("${waf.zg_did}")
    public String zgDid;
    @Value("${waf.zg_08c5bcee6e9a4c0594a5d34b79b9622a}")
    public String zg;
    @Value("${waf.X_CSRF_TOKEN}")
    public String csrfToken;
    @Value("${waf.cookie}")
    public String cookie;
}
