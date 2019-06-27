package com.github.springbootmonitor.pojo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 创建时间为 17:32 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRemoteDO {

    private String host;

    private String ip;

    private String proxy;

    private Integer status;

    private String title;

    private String md5;

    private Boolean http;

    private Boolean access;

    private String desc;

    private String html;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
