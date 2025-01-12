package com.lxbigdata.be.controller;

import com.lxbigdata.be.pojo.Category;
import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.lxbigdata.be.controller
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    //查询的是当前用户所创建的所有分类
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        category.setUpdateTime(LocalDateTime.now());
        categoryService.update(category);
        return Result.success();
    }

}
