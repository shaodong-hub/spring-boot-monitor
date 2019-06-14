package com.github.springbootmonitor.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 创建时间为 15:47 2019-06-10
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CsvItemDO {

    @NotBlank
    private String host;

    private String ipSource;

    @NotBlank
    private String ipCdn;

    @NotBlank
    private String ipWaf;

    @NotBlank
    private Boolean http;

    @NotBlank
    private String desc;

}
