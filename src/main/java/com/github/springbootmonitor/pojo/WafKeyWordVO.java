package com.github.springbootmonitor.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: Du Jiahao
 * @Date: 2019/7/2 0002 19:06
 */
@Data
@Builder
@ToString
public class WafKeyWordVO {
    private String domainKey;
    private String orgCode;
}
