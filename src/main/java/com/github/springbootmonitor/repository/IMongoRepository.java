package com.github.springbootmonitor.repository;

import com.github.springbootmonitor.pojo.MongoItemDO;

import java.util.List;


/**
 * @Author: Du Jiahao
 * @Date: 2019/6/27 0027 15:56
 */
public interface IMongoRepository {
    /**
     * 根据域名获取网站内容数据
     * @param host 域名
     * @param collection  集合
     * @return MongoItemDO
     */
    MongoItemDO getContentByHost(String host, String collection);

    /**
     * 获取集合内的所有记录
     * @param collection 集合名称
     * @return List<MongoItemDO>
     */
    List<MongoItemDO> getAll(String collection);

}
