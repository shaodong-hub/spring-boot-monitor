package com.github.springbootmonitor.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 创建时间为 17:11 2019-06-04
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
@Document("MongoItemDO")
public class MongoItemDO {

    @Id
    private String host;

    @Field("ip_source")
    private String ipSource;

    @Field("ip_cdn")
    private String ipCdn;

    @Field("ip_waf")
    private String ipWaf;

    private Boolean http;

    private String desc;

    private Integer status;

    private String title;

    private Map<String, String> md5;

    private Map<String, String> html;

    @Indexed
    @Field("access_source")
    private Boolean accessSource;

    @Indexed
    @Field("access_cdn")
    private Boolean accessCdn;

    @Indexed
    @Field("access_waf")
    private Boolean accessWaf;

    @Indexed
    private Boolean defend;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;

}
