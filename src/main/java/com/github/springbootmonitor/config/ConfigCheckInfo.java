package com.github.springbootmonitor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 创建时间为 11:51 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "com.cmcc")
public class ConfigCheckInfo {

    private Map<String, String> cdn;

    private Map<String, String> waf;

}
