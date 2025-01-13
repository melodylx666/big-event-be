package com.lxbigdata.be.mapper;

import com.lxbigdata.be.pojo.Article;
import org.apache.ibatis.annotations.*;

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

    @Select("select id,title,content,cover_img,state,category_id,create_time,update_time from article where id = #{id} and create_user = #{userId}")
    Article findById(Integer id, Integer userId);

    @Update("update article set title = #{article.title},content = #{article.content},cover_img = #{article.coverImg},state = #{article.state},category_id = #{article.categoryId},update_time = now() where id = #{article.id} and create_user = #{userId}")
    void update(Article article, Integer userId);

    @Delete("delete from article where id = #{id} and create_user = #{userId}")
    void delete(Integer id, Integer userId);
}
