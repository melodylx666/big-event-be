package com.lxbigdata.be.service;

import com.lxbigdata.be.pojo.Article;
import com.lxbigdata.be.pojo.PageBean;

/**
 * ClassName: ArticleService
 * Package: com.lxbigdata.be.service
 * Description:
 *
 * @author lx
 * @version 1.0
 */
public interface ArticleService {
    //增
    void add(Article article);

    //分页查询,此处是动态sql，应该使用动态配置文件
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //查询单个文章详情
    Article findById(Integer id);
    //改
    void update(Article article);
    //删
    void delete(Integer id);
}
