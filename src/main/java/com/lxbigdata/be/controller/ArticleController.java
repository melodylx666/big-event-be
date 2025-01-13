package com.lxbigdata.be.controller;

import com.lxbigdata.be.pojo.Article;
import com.lxbigdata.be.pojo.PageBean;
import com.lxbigdata.be.pojo.Result;
import com.lxbigdata.be.service.ArticleService;
import com.lxbigdata.be.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: ArticleController
 * Package: com.lxbigdata.be.controller
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);

    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id){
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
