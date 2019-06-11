package com.github.springbootmonitor.common;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * 创建时间为 17:30 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
public class InetAddressUtils {

    public static String getIP(String url) {
        try {
            return InetAddress.getByName(url).getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("url:{} 无法解析源地址", url);
            return null;
        }
    }

    public static InetAddress getInetAddress(String url) {
        try {
            return InetAddress.getByName(url);
        } catch (UnknownHostException e) {
            log.warn("url:{} 无法解析源地址", url);
            return null;
        }
    }

}
