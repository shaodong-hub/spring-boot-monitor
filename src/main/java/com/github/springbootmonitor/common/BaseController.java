package com.github.springbootmonitor.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: Du Jiahao
 * @Date: 2019/6/27 0027 18:50
 */
public class BaseController {
    protected HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
}
