package com.github.springbootmonitor.controller;

import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.ResultDO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:22
 */
public interface IMongoWafController {

    ResultDO<FileInfoDO> upload(MultipartFile file);


}
