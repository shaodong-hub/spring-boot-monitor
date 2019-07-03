package com.github.springbootmonitor.pojo;

import lombok.*;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 15:01
 */
@Data
@ToString
public class WafRequest {
    private Boolean check;
    private String account;
    private String property;
    private String areaFlag;
    private String domain;
    private Integer isHttps;
    private Integer httpsForcedJump;
    private Integer httpSource;
    private String httpsCrt;
    private String httpsKey;
    private Integer backType;
    private String backendAddr;
    private Integer port;
    private Integer httpsPort;
    private Integer agentState;
    private Integer balancing;

    public WafRequest(){}
    public WafRequest(XlsDO xlsDO){
        this.setCheck(Boolean.FALSE);
        this.setDomain(xlsDO.getHost());
        this.setIsHttps(1);
        this.setHttpsForcedJump(0);
        this.setBackType(0);
        this.setBackendAddr(xlsDO.getIp());
        this.setPort(80);
    }
}
