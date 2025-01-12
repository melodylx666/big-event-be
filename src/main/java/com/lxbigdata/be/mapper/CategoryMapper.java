package com.lxbigdata.be.mapper;

import com.lxbigdata.be.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.lxbigdata.be.mapper
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Mapper
public interface CategoryMapper {
    //增
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
    "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    @Select("select * from category where id=#{id} and create_user=#{userId}")
    Category findById(Integer id, Integer userId);

    @Update("update category set category_name=#{category.categoryName},category_alias=#{category.categoryAlias},update_time=#{category.updateTime} " +
            "where id=#{category.id} and create_user=#{userId}")
    void update( Category category,  Integer userId);
}
