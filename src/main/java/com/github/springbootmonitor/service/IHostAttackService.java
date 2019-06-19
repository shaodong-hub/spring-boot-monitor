package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.MongoItemDO;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/19 0019 11:14
 */
public interface IHostAttackService {
    /**
     * 攻击网站获取网站信息
     * @param itemDO 域名
     * @return MongoItemDO
     */
    MongoItemDO getRemoteInfoByAttack(MongoItemDO itemDO);
}


