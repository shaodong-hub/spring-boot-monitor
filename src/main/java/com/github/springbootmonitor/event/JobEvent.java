package com.github.springbootmonitor.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 创建时间为 17:06 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Getter
@Setter
public class JobEvent extends ApplicationEvent {

    private String collection;

    public JobEvent(Object source, String collection) {
        super(source);
        this.collection = collection;
    }
}
