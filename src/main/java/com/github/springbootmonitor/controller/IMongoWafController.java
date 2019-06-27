package com.github.springbootmonitor.controller;

import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResultDO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:22
 */
public interface IMongoWafController {

    /**
     * 上传待添加到waf平台的域名及源站的csv文件
     * @param file csv文件
     * @return FileInfoDO
     */
    ResultDO<FileInfoDO> upload(MultipartFile file);

    /**
     * 根据集合和域名获取网站访问源码
     * @return MonItemDO
     */
    ResultDO<MongoItemDO> getWebsiteContents();

}
