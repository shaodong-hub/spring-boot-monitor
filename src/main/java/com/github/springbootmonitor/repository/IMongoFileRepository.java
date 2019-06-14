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

    /**
     * 获取所有的文件信息
     *
     * @return Set
     */
    Set<FileInfoDO> getAllFileNames();

    /**
     * 保存文件
     *
     * @param name        文件名称
     * @param inputStream 输入流
     * @return String
     */
    String saveFile(String name, InputStream inputStream);

    /**
     * 根据文件名称获取文件
     *
     * @param name 文件名
     * @return InputStream
     */
    InputStream getByName(String name);

    /**
     * 删除文件
     *
     * @param name 文件名称
     */
    void delete(String name);

}
