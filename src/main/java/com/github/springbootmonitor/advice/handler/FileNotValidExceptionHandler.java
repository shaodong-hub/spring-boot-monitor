package com.github.springbootmonitor.advice.handler;

import com.github.springbootmonitor.advice.FileNotValidException;
import com.github.springbootmonitor.pojo.ResultDO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * 文件不合法异常处理器
 *
 * <p>
 * 创建时间为 00:18 2019-06-06
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@RestControllerAdvice
public class FileNotValidExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = FileNotValidException.class)
    public ResultDO<Void> handler(FileNotValidException exception) {
        return ResultDO.<Void>builder().message("File Not Valid!").status(2).build();
    }

}
