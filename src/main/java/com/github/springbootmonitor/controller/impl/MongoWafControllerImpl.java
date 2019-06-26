package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.controller.IMongoWafController;
import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IWafService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:24
 */
@Api(tags = "针对Waf操作的相关接口")
@RestController
@RequestMapping("/waf")
public class MongoWafControllerImpl implements IMongoWafController {

    @Resource
    private IWafService service;

    @Override
    @PostMapping("/file/upload")
    @ApiOperation("上传文件接口")
    public ResultDO<FileInfoDO> upload(MultipartFile file) {
        return service.upload(file);
    }

}
