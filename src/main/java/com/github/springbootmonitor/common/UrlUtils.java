package com.github.springbootmonitor.common;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 创建时间为 15:27 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public class UrlUtils {

    private static final String SUFFIX = "/";

    public static final String PREFIX_HTTP = "http://";

    public static final String PREFIX_HTTPS = "https://";

    /**
     * 检查 host 是否包前缀,包含就删除
     *
     * @param url host
     * @return String
     */
    public static String hostNormalizer(String url) {
        if (StringUtils.startsWith(url, PREFIX_HTTP)) {
            String temp = StringUtils.remove(url, PREFIX_HTTP);
            return StringUtils.remove(temp, SUFFIX);
        }
        if (StringUtils.startsWith(url, PREFIX_HTTPS)) {
            String temp = StringUtils.remove(url, PREFIX_HTTP);
            return StringUtils.remove(temp, SUFFIX);
        }
        return url;
    }


    /**
     * 根据主机获取对应的url
     *
     * @param host 主机
     * @param http 是否为http
     * @return String
     */
    public static String joining(String host, boolean http) {
        String temp = hostNormalizer(host);
        return http ? PREFIX_HTTP + temp : PREFIX_HTTPS + temp;
    }

}
