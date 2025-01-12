package com.lxbigdata.be.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分类实体类，对应数据库的category表
 * 由于添加分类和修改分类的校验逻辑不同，则可以分组校验
 * 如果没有指定，默认属于default分组，并且分组间可以有继承关系(extends default等)
 * 1.定义内部分组接口
 * 2.POJO类：在注解的group属性中指定分组
 * 3.Controller：在校验注解(Controller的Validation注解)中指定分组
 */
@Data
public class Category {
    @NotNull(groups = {Update.class})
    private Integer id;//主键ID
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    public interface Add {
    }

    public interface Update {
    }
}
