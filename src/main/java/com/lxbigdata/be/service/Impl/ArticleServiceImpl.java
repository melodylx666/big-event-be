package com.lxbigdata.be.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxbigdata.be.mapper.ArticleMapper;
import com.lxbigdata.be.pojo.Article;
import com.lxbigdata.be.pojo.PageBean;
import com.lxbigdata.be.service.ArticleService;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.lxbigdata.be.service.Impl
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        var map = ThreadLocalUtil.<Map<String,Object>>get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean来封装返回数据
        var pageBean = new PageBean<Article>();
        //开启分页查询,使用MyBatisPlus的分页插件
        PageHelper.startPage(pageNum,pageSize);

        //调用Mapper
        var map = ThreadLocalUtil.<Map<String,Object>>get();
        Integer userId = (Integer) map.get("id");
        List<Article> alist =  articleMapper.list(userId,categoryId,state);
        //Page中提供了方法，所以需要强转为Page(外部引入的类)
        Page<Article> page = (Page<Article>) alist;
        //将数据填充到pageBean中
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;
    }

    @Override
    public Article findById(Integer id) {
        var map = ThreadLocalUtil.<Map<String,Object>>get();
        Integer userId = (Integer) map.get("id");
        Article article = articleMapper.findById(id,userId);
        return article;
    }

    @Override
    public void update(Article article) {
        var map = ThreadLocalUtil.<Map<String,Object>>get();
        Integer userId = (Integer) map.get("id");
        articleMapper.update(article,userId);
    }

    @Override
    public void delete(Integer id) {
        var map = ThreadLocalUtil.<Map<String,Object>>get();
        Integer userId = (Integer) map.get("id");
        articleMapper.delete(id,userId);
    }

}
