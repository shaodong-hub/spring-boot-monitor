package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.controller.IMongoFileController;
import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.service.IMongoFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@Api(tags = "文件相关接口")
@RestController
public class MongoFileControllerImpl implements IMongoFileController {

    @Resource
    private IMongoFileService service;

    @Override
    @GetMapping("/file")
    @ApiOperation("获取所有文件名称")
    public ResultDO<Set<FileInfoDO>> getAllFileNames() {
        return service.getAllFileNames();
    }

    @Override
    @PostMapping("/file/upload")
    @ApiOperation("文件上传接口")
    public ResultDO<FileInfoDO> upload(MultipartFile file) {
        return service.upload(file);
    }

    @Override
    @GetMapping("/file/download/{name}")
    @ApiOperation("下载文件")
    public void download(@PathVariable String name, HttpServletResponse response) {
        service.download(name, response);
    }

    @Override
    @GetMapping("/file/download/result/{name}")
    @ApiOperation("导出处理后的csv文件")
    public void downloadResults(@PathVariable String name, HttpServletResponse response) {
        service.downloadResults(name, response);
    }

    @Override
    @DeleteMapping("/file/{name}")
    @ApiOperation("删除文件")
    public ResultDO<Void> delete(@PathVariable String name) {
        return service.delete(name);
    }

}
