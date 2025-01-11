package com.lxbigdata.be.controller;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: ArticleController
 * Package: com.lxbigdata.be.controller
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(
//            @RequestHeader(name = "Authorization") String token,
//            HttpServletResponse response
    ) {
        //根据接口文档，则token需要放在请求头的Authorization字段
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有的文章");
//        } catch (Exception e) {
//            //HTTP相应码为401
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("所有的文章");
    }
}
