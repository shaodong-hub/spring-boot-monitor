package com.github.springbootmonitor.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: Du Jiahao
 * @Date: 2019/7/2 0002 17:52
 */
@Data
@ToString
public class WafHostVO {
    private String domain;
    private String id;
}
