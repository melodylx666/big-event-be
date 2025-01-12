package com.lxbigdata.be.service.Impl;

import com.lxbigdata.be.mapper.UserMapper;
import com.lxbigdata.be.pojo.User;
import com.lxbigdata.be.service.UserService;
import com.lxbigdata.be.utils.Md5Util;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * ClassName: UserServiceImpl
 * Package: com.lxbigdata.be.service.Impl
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //密码需要进行加密存储,DB中存储的密码不能是明文
        String md5String = Md5Util.getMD5String(password);
        //添加用户
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        //由于无法从其他地方获取用户信息，只能从ThreadLocal中获取用户信息
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,userId);
    }

    @Override
    public void updatePwd(String new_pwd) {
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(new_pwd),userId);
    }


}
