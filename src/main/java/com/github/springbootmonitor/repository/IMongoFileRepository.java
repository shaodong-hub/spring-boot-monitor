package com.github.springbootmonitor.repository;

import com.github.springbootmonitor.pojo.FileInfoDO;

import java.io.InputStream;
import java.util.Set;

/**
 * <p>
 * 创建时间为 17:57 2019-06-04
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public interface IMongoFileRepository {

    Set<FileInfoDO> getAllFileNames();

    String saveFile(String name, InputStream inputStream);

    InputStream getByName(String name);

    void delete(String name);

}
