package com.lxbigdata.be.mapper;

import com.lxbigdata.be.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ArticleMapper
 * Package: com.lxbigdata.be.mapper
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Mapper
public interface ArticleMapper {
    //增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            " values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);
}
