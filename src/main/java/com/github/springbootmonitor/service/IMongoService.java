package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.MongoItemDO;
import com.github.springbootmonitor.pojo.ResultDO;

import java.util.List;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/27 0027 16:39
 */
public interface IMongoService {

    /**
     * 根据文件和域名获取访问网站的源码
     * @param collection 文件名
     * @param host 域名
     * @return ResultDO<MongoItemDO>
     */
    ResultDO<MongoItemDO> getByHost(String collection, String host);

    /**
     * 找到源站md5和waf访问网站md5不同的所有域名列表
     * @param collection
     * @return
     */
    ResultDO<List<String>> getListContentNotConsistent(String collection);
}
