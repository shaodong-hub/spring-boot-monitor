package com.github.springbootmonitor.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 创建时间为 14:38 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */
@Controller
@RequestMapping
public class HtmlController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

}
