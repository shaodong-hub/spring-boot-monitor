package com.github.springbootmonitor.controller;

import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.ResultDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <p>
 * 创建时间为 18:35 2019-06-04
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IMongoFileController {

    /**
     * 获取所有的文件名
     *
     * @return 文件名称
     */
    ResultDO<Set<FileInfoDO>> getAllFileNames();

    /**
     * @param file 上传文件
     * @return 文件信息
     */
    ResultDO<FileInfoDO> upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param name     文件名称
     * @param response 返回流
     */
    void download(String name, HttpServletResponse response);

    /**
     * 下载处理后的文件
     * @param name  文件名称
     * @param response 返回流
     */
    void downloadResults(String name, HttpServletResponse response);

    /**
     * 删除文件
     *
     * @param name 文件名称
     * @return void
     */
    ResultDO<Void> delete(String name);

}
