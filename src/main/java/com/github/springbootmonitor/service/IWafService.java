package com.github.springbootmonitor.service;

import com.github.springbootmonitor.pojo.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *  针对Waf系统操作的相关接口
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 9:26
 */
public interface IWafService {

    /**
     * 上传waf域名配置文件
     * @param file 文件
     * @return FileInfoDO
     */
    ResultDO<FileInfoDO> upload(MultipartFile file);

    /**
     * 将域名配置添加到Waf
     * @param item 域名信息
     * @return WafItemDO
     */
    WafItemDO insert2Waf(XlsDO item);

    /**
     * 从waf平台中删除域名配置
     * @param item 域名信息
     * @return WafItemDO
     */
    WafItemDO deleteFromWaf(XlsDO item);

}
