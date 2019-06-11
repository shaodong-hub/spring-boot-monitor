package com.github.springbootmonitor.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.Serializable;

/**
 * <p>
 * 创建时间为 18:40 2019-06-04
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
public class ResultDO<T> implements Serializable {

    @JsonIgnore
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final Integer SUCCESS = 0;

    public static final Integer FAIL = 1;

    private T data;

    private String message;

    private Integer status;

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public String toString() {
        return MAPPER.writeValueAsString(this);
    }

    /**
     * 结果状态
     */
    public enum StatusEnum {

        /**
         * 成功
         */
        SUCCESS("SUCCESS"),

        /**
         * 失败
         */
        FAIL("FAIL");

        private String message;

        StatusEnum(String message) {
            this.message = message;
        }

        @Override
        public String toString(){
            return message;
        }


    }


}