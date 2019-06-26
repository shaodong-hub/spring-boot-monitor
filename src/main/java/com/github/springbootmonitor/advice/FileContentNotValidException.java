package com.github.springbootmonitor.advice;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/14 0014 18:43
 */
public class FileContentNotValidException extends RuntimeException {
    public FileContentNotValidException(String msg){
        super(msg);
    }
    public FileContentNotValidException(){super();}
}
