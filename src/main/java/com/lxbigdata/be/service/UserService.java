package com.lxbigdata.be.service;

import com.lxbigdata.be.pojo.User;

/**
 * ClassName: UserService
 * Package: com.lxbigdata.be.service
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public interface UserService {

    // 根据用户名查询用户
    User findByUserName(String username);
    // 注册
    void register(String username, String password);

    //修改
    void update(User user);

    // 修改头像
    void updateAvatar(String avatarUrl);
    //更新密码
    void updatePwd(String new_pwd);
}
