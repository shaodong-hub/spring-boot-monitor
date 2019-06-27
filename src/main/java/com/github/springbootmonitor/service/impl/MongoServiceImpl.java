package com.github.springbootmonitor.service.impl;

import com.github.springbootmonitor.pojo.FileInfoDO;
import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResultDO;
import com.github.springbootmonitor.repository.IMongoRepository;
import com.github.springbootmonitor.service.IMongoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/27 0027 16:44
 */
@Service
public class MongoServiceImpl implements IMongoService {

    @Resource
    private IMongoRepository repository;

    @Override
    public ResultDO<MongoItemDO> getByHost(String collection, String host) {
        MongoItemDO item = repository.getContentByHost(host, collection);
        return ResultDO.<MongoItemDO>builder()
                .data(item)
                .message(ResultDO.StatusEnum.SUCCESS.toString())
                .status(0)
                .build();
    }
}
