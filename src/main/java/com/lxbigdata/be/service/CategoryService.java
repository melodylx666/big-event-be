package com.lxbigdata.be.service;

import com.lxbigdata.be.pojo.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.lxbigdata.be.service
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public interface CategoryService {
    //添加分类
    void add(Category category);
    //查询该用户的所有分类
    List<Category> list();
    //查询具体的文章分类
    Category findById(Integer id);
    //改
    void update(Category category);
}
