package com.lxbigdata.be.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类，用于对应数据库的user表
 */
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore //转换为json的时候忽略这个属性
    private String password;//密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    //这里是大驼峰命名，但是数据库表里面是下划线,需要在配置文件中配置一下，已经解决
    private LocalDateTime createTime;//创建时间,
    private LocalDateTime updateTime;//更新时间
}
