package com.lxbigdata.be.pojo;



import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类，用于对应数据库的user表
 */
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间,这里是大驼峰命名，但是数据库表里面是下划线
    private LocalDateTime updateTime;//更新时间
}
