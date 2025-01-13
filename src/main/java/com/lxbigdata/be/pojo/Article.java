package com.lxbigdata.be.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lxbigdata.be.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

/**
 * 文章实体类，对应数据库中的article表
 * 包括自定义校验：
 * 1.定义一个注解，在anno包下
 * 2.定义一个校验器，在validation包下
 * 3.在实体类上使用注解
 *
 */
@Data
public class Article {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题
    @NotEmpty
    private String content;//文章内容
    private String coverImg;//封面图像
    @State
    private String state;//发布状态 已发布|草稿
    @NotNull
    private Integer categoryId;//文章分类id
    @JsonIgnore
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
