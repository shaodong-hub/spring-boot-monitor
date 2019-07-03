package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.*;
import com.github.springbootmonitor.repository.IMongoFileRepository;
import com.github.springbootmonitor.repository.IRemoteHostRepository;
import com.github.springbootmonitor.service.IWafService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:34
 */
@Service
public class WafServiceImpl implements IWafService {

    @Resource
    private IMongoFileRepository repository;

    @Resource
    private IRemoteHostRepository hostRepository;

    @Override
    @SneakyThrows(IOException.class)
    public ResultDO<FileInfoDO> upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        // 重复文件名 400 错误
        if(repository.existByName(name)){
            throw new FileAlreadyExistsException("");
        }
        // 略过格式校验
        // List<String> list = FilesUtils.readAllLines(file.getInputStream());
        repository.saveFile(name, file.getInputStream());
        return ResultDO.<FileInfoDO>builder()
                .data(FileInfoDO.builder().name(name).lastModified(new Date()).build())
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }

    @Override
    public WafItemDO insert2Waf(XlsDO item) {
        WafResponse result = hostRepository.importHosts2Waf(item);
        return WafItemDO.builder()
                .host(item.getHost())
                .ip(item.getIp())
                .status(result.getResultCode()==0)
                .message(result.getMessage())
                .build();
    }

    @Override
    public WafItemDO deleteFromWaf(XlsDO item) {
        WafResponse result = hostRepository.deleteHostFromWaf(item);
        return WafItemDO.builder()
                .host(item.getHost())
                .status(result.getResultCode()==0)
                .message(result.getMessage())
                .build();
    }

}
