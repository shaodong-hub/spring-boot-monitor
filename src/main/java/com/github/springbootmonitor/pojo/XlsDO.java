package com.github.springbootmonitor.pojo;

import lombok.*;
import org.hibernate.validator.constraints.pl.REGON;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 10:46
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class XlsDO {
    @NotBlank
    private String host;

    @NotBlank
    @Pattern(regexp = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))")
    private String ip;

}
