package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.common.BaseController;
import com.github.springbootmonitor.controller.IMongoWafController;
import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IMongoService;
import com.github.springbootmonitor.service.IWafService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:24
 */
@Api(tags = "针对Waf操作的相关接口")
@RestController
@RequestMapping("/waf")
public class MongoWafControllerImpl extends BaseController implements IMongoWafController {

    @Resource
    private IWafService service;

    @Resource
    private IMongoService mongoService;

    @Override
    @PostMapping("/file/upload")
    @ApiOperation("上传文件接口")
    public ResultDO<FileInfoDO> upload(MultipartFile file) {
        return service.upload(file);
    }

    @Override
    @GetMapping("/webContent")
    @ApiOperation("获取访问网站的源码接口")
    public ResultDO<MongoItemDO> getWebsiteContents() {
        HttpSession session = getSession();
        String collection = session.getAttribute("collection").toString();
        String host = session.getAttribute("host").toString();
        return mongoService.getByHost(collection, host);
    }

    @Override
    @GetMapping("/web-source/list/{collection}")
    public ResultDO<List<String>> getListContentNotConsistent(@PathVariable("collection") String collection) {
        return mongoService.getListContentNotConsistent(collection);
    }


}
