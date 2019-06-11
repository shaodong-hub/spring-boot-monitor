package com.github.springbootmonitor.common;

import org.apache.commons.codec.digest.DigestUtils;

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


public class MD5Utils {

    public static String getMD5String(String result) {
        return DigestUtils.md5Hex(result);
    }

}
