package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.advice.FileContentNotValidException;
import com.github.springbootmonitor.common.*;
import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.repository.IMongoFileRepository;
import com.github.springbootmonitor.service.IMongoFileService;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

/**
 * <p>
 * 创建时间为 18:43 2019-06-04
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Service
public class MongoFileServiceImpl implements IMongoFileService {

    @Resource
    private IMongoFileRepository repository;

    @Override
    public ResultDO<Set<FileInfoDO>> getAllFileNames() {
        Set<FileInfoDO> set = Sets.newTreeSet(Comparator.comparing(FileInfoDO::getLastModified));
        set.addAll(repository.getAllFileNames());
        return ResultDO.<Set<FileInfoDO>>builder()
                .data(set)
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }

    @Override
    @SneakyThrows(IOException.class)
    public ResultDO<FileInfoDO> upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        // 重复文件名 400 错误
        if(repository.existByName(name)){
            throw new FileAlreadyExistsException("");
        }
        List<String> list = FilesUtils.readAllLines(file.getInputStream());
        // 逐行校验格式
        for(String row :list){
            if(!ItemsValidateUtils.validate(row)){
                throw new FileContentNotValidException(ErrorMsgs.CONTENT_NOT_VALID);
            }
        }
        repository.saveFile(name, file.getInputStream());
        return ResultDO.<FileInfoDO>builder()
                .data(FileInfoDO.builder().name(name).lastModified(new Date()).build())
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }

    @Override
    @SneakyThrows(IOException.class)
    public ResultDO<Void> download(String name, HttpServletResponse response) {
        InputStream inputStream = repository.getByName(name);
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/x-download");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + name);
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        return ResultDO.<Void>builder()
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }

    @Override
    public ResultDO<Void> downloadResults(String name, HttpServletResponse response) {

        List<MongoItemDO> list = repository.getResultsByName(name);
        String headLine = ExportCsvUtils.getHeadLine();
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map;
        for (MongoItemDO item : list) {
            map = ReflectUtils.getKeyValueMap(item);
            dataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportCsvUtils.responseSetProperties(name, response);
            ExportCsvUtils.doExport(dataList, headLine, headLine, os);
        } catch (Exception e) {
            log.error(ErrorMsgs.IMPORT_CSV_FAILED, e);
        }
        return ResultDO.<Void>builder()
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }

    @Override
    public ResultDO<Void> delete(String name) {
        repository.delete(name);
        return ResultDO.<Void>builder()
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }


}
