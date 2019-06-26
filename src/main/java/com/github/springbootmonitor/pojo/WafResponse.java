package com.github.springbootmonitor.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 16:17
 */
@Data
@ToString
@Builder
@AllArgsConstructor
public class WafResponse {
    private Integer resultCode;
    private String message;
    private String result;
}
