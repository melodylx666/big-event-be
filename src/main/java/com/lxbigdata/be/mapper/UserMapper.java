package com.lxbigdata.be.mapper;

import com.lxbigdata.be.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: UserMapper
 * Package: com.lxbigdata.be.mapper
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    //查
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);
    //增
    @Insert("insert into user(username,password,create_time,update_time)" +
            "values(#{username},#{password},now(),now())")
    void add(String username, String password);
}
