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


    User findByUserName(String username);

    void register(String username, String password);
}
