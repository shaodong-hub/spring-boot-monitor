package com.github.springbootmonitor.controller;

/**
 * <p>
 * 创建时间为 17:43 2019-06-11
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IMongoHtmlController {

    /**
     * 返回上传网页
     *
     * @return index
     */
    String index();

    /**
     * waf 文件上传网页
     * @return String
     */
    String wafUpload();

    /**
     * 根据网站域名比较网站内容
     * @param collection 集合
     * @param webHost  网站域名
     * @return  String
     */
    String websiteDiff(String collection, String webHost);

}
