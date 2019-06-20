package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.MongoItemDO;

/**
 * @Author Du Jiahao
 */
public interface IHostWafService {
    MongoItemDO getRemoteInfoByWaf(MongoItemDO itemDO);
}
