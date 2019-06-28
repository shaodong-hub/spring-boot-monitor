package com.github.springbootmonitor.repository.impl;

import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.repository.IMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/27 0027 16:47
 */
@Repository
public class MongoRepository implements IMongoRepository {

    @Resource
    private MongoTemplate template;

    @Override
    public MongoItemDO getContentByHost(String host, String collection) {
        return template.findById(host, MongoItemDO.class, collection);
    }

    @Override
    public List<MongoItemDO> getAll(String collection) {
        return template.findAll(MongoItemDO.class, collection);
    }

}
