package com.github.springbootmonitor.pojo;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/24 0024 10:58
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("WafItemDO")
public class WafItemDO {
    @Id
    private String host;

    @Field("ip")
    private String ip;

    @Field("status")
    private Boolean status;

    @Field("message")
    private String message;

    @CreatedDate
    @Field("created_date")
    private Date createdDate;
}
