package com.github.springbootmonitor.controller.impl;

import com.github.springbootmonitor.common.BaseController;
import com.github.springbootmonitor.controller.IMongoHtmlController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

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
@Api(tags = "返回页面的接口")
public class MongoHtmlControllerImpl extends BaseController implements IMongoHtmlController {

    @Override
    @GetMapping("index")
    @ApiOperation(value = "获取文件上传页面", httpMethod = "GET", notes = "获取文件上传页面")
    @ApiResponses({
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "获取为空")
    })
    public String index() {
        return "index";
    }

    @Override
    @GetMapping("/waf/index")
    @ApiOperation(value = "waf文件上传页面", httpMethod = "GET", notes = "上传waf文件页面")
    public String wafUpload() {
        return "waf-upload";
    }

    @Override
    @GetMapping("/diff/{collection}/{host}")
    @ApiOperation("网站内容文本比对")
    public String websiteDiff(@PathVariable("collection") String collection, @PathVariable("host")  String host) {
        HttpSession session = getSession();
        session.setAttribute("collection", collection);
        session.setAttribute("host", host);
        return "diff";
    }
}
