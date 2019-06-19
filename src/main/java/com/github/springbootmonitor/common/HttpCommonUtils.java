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
        headers.add(new BasicHeader("X-CSRF-TOKEN", "58bf7769-d695-4178-ab9d-5474e89fe3bf"));
        headers.add(new BasicHeader("Cookie", "SESSION=ZDBiNjA3MDMtYjlkOC00NTllLWI5NTEtZjEwYjc1YzVkMTE5; zg_did=%7B%22did%22%3A%20%221664e73ad30a25-056f43142c3c84-333b5602-100200-1664e73ad311ce%22%7D; zg_08c5bcee6e9a4c0594a5d34b79b9622a=%7B%22sid%22%3A%201538914495801%2C%22updated%22%3A%201538914516024%2C%22info%22%3A%201538914495810%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%7D"));
        return headers;
    }

}
