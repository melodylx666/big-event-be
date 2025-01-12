package com.lxbigdata.be.mapper;

import com.lxbigdata.be.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);
    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
