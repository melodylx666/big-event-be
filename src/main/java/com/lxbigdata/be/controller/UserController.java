package com.lxbigdata.be.controller;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.pojo.User;
import com.lxbigdata.be.service.UserService;
import com.lxbigdata.be.utils.JwtUtil;
import com.lxbigdata.be.utils.Md5Util;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: UserController
 * Package: com.lxbigdata.be.controller
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    // 注入service
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(
            @Pattern(regexp = "^\\S{5,16}$") String username,
            @Pattern(regexp = "^\\S{5,16}$") String password
            ) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //没有被占用
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(
            @Pattern(regexp = "^\\S{5,16}$") String username,
            @Pattern(regexp = "^\\S{5,16}$") String password
    ){
        System.out.println(username);
        System.out.println(password);
        User loginUser = userService.findByUserName(username);
        System.out.println(loginUser);
        if(loginUser == null){
            return Result.error("用户名不存在");
        }
        if(!Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            return Result.error("密码错误");
        }
        //登录成功
        var map = new HashMap<String, Object>();
        map.put("id",loginUser.getId());
        map.put("username",loginUser.getUsername());

        String token = JwtUtil.genToken(map);
        return Result.success(token);
    }
}
