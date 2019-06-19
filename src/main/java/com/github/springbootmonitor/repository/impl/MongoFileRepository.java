package com.github.springbootmonitor.repository.impl;

import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.repository.IMongoFileRepository;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

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
@Repository
public class MongoFileRepository implements IMongoFileRepository {

    @Resource
    private GridFsOperations operations;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    @SneakyThrows(IOException.class)
    public Set<FileInfoDO> getAllFileNames() {
        GridFsResource[] resources = operations.getResources("*");
        Set<FileInfoDO> set = Sets.newHashSet();
        for (GridFsResource resource : resources) {
            FileInfoDO fileInfoDO = FileInfoDO.builder()
                    .name(resource.getFilename())
                    .contentLength(resource.contentLength())
                    .description(resource.getDescription())
                    .lastModified(new Date(resource.lastModified()))
                    .build();
            set.add(fileInfoDO);
        }
        return set;
    }

    @Override
    public String saveFile(String name, InputStream inputStream) {
        operations.store(inputStream, name);
        return name;
    }

    @Override
    @SneakyThrows(IOException.class)
    public InputStream getByName(String name) {
        GridFsResource resource = operations.getResource(name);
        return resource.getInputStream();
    }

    @Override
    public List<MongoItemDO> getResultsByName(String name) {
        return mongoTemplate.findAll(MongoItemDO.class, name);
    }

    @Override
    public void delete(String name) {
        operations.delete(new Query(where("filename").is(name)));
    }

    @Override
    public boolean existByName(String name) {
        return operations.findOne(new Query(where("filename").is(name))) != null;
    }

}
