package com.lxbigdata.be.service.Impl;

import com.lxbigdata.be.mapper.CategoryMapper;
import com.lxbigdata.be.pojo.Category;
import com.lxbigdata.be.service.CategoryService;
import com.lxbigdata.be.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.lxbigdata.be.service.Impl
 * Description:
 *
 * @author lx
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer)map.get("id");
        category.setCreateUser(userId);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer)map.get("id");
        List<Category> cs = categoryMapper.list(userId);
        return cs;

    }

    @Override
    public Category findById(Integer id) {
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer)map.get("id");
        Category c = categoryMapper.findById(id,userId);
        return c;
    }

    @Override
    public void update(Category category) {
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer)map.get("id");
        categoryMapper.update(category,userId);
    }

    @Override
    public void delete(Integer id) {
        var map = ThreadLocalUtil.<Map<String, Object>>get();
        var userId = (Integer)map.get("id");
        categoryMapper.delete(id,userId);

    }
}
