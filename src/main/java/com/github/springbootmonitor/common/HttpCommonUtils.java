package com.github.springbootmonitor.common;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

public class HttpCommonUtils {

    /**
     * 添加内容转换器
     *
     * @return List
     */
    public static List<HttpMessageConverter<?>> getHttpMessageConverters() {
        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        return messageConverters;
    }

    /**
     * 设置请求头
     *
     * @return List
     */
    public static List<Header> getHeaders() {
        // 设置请求头
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        headers.add(new BasicHeader("content-type", "application/json"));
        headers.add(new BasicHeader("X-CSRF-TOKEN", "f3da1466-65d4-4f21-9602-780d47d94adb"));
        headers.add(new BasicHeader("Cookie", "SESSION=NDI3OTlkNmUtMWZlYy00NmQ3LWE5OWYtN2Q0MjBmNjRiMDg3"));
        return headers;
    }

}
