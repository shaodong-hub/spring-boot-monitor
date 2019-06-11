package com.github.springbootmonitor.advice.handler;

import com.github.springbootmonitor.pojo.ResultDO;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 创建时间为 17:07 2019-06-05
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@RestControllerAdvice
public class JobAlreadyCompleteExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JobInstanceAlreadyCompleteException.class)
    public ResultDO<Void> handler(JobInstanceAlreadyCompleteException exception) {
        return ResultDO.<Void>builder().message("Job Already Complete!").status(2).build();
    }


}
