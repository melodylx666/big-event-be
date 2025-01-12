package com.lxbigdata.be.controller;

import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.pojo.User;
import com.lxbigdata.be.service.UserService;
import com.lxbigdata.be.utils.JwtUtil;
import com.lxbigdata.be.utils.Md5Util;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
    ) {
        User loginUser = userService.findByUserName(username);
        System.out.println(loginUser);
        if (loginUser == null) {
            return Result.error("用户名不存在");
        }
        if (!Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            return Result.error("密码错误");
        }
        //登录成功
        var map = new HashMap<String, Object>();
        map.put("id", loginUser.getId());
        map.put("username", loginUser.getUsername());

        String token = JwtUtil.genToken(map);
        //把token同时存储到redis中,此处token作为键与值同时存储
        stringRedisTemplate.opsForValue().set(token, token, 12, TimeUnit.HOURS);
        return Result.success(token);
    }

    /**
     * 根据有效的token获取用户信息，此处必然成功
     *
     * @param token
     * @return 用户信息
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户名查询用户信息
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var username = (String) map.get("username");
        var user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //此处这里因为上传的数据不是User，则需要使用其他格式接受
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params,
                            @RequestHeader(name = "Authorization") String token
    ) {
        //手动校验参数，这里validation提供的注解无法满足要求，需要手动校验
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)) {
            return Result.error("缺少必要的参数");
        }
        //原密码是否正确
        String username = ThreadLocalUtil.<Map<String, Object>>get().get("username").toString();
        User loginUser = userService.findByUserName(username);
        if (!Md5Util.getMD5String(old_pwd).equals(loginUser.getPassword())) {
            return Result.error("原密码错误");
        }
        //确认密码是否一致
        if (!new_pwd.equals(re_pwd)) {
            return Result.error("两次密码不一致");
        }
        //进行更新
        userService.updatePwd(new_pwd);

        //删除redis中的token
        var ops = stringRedisTemplate.opsForValue();
        Boolean delete = ops.getOperations().delete(token);
        if(!delete){
            return Result.error("token已失效");
        }
        return Result.success();
    }
}
