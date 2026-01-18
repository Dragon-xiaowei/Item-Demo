package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.PathIterator;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult SreachCategory(CategoryPageQueryDTO categoryPageQueryDTO) {

        //pagehapler分页插件进行分页查询
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category> categories = categoryMapper.sreachCategroy(categoryPageQueryDTO);
        //获取总条数
        long total = categories.getTotal();
        //获取所有categroy数据
        List<Category> categoryList = categories.getResult();

        return  new PageResult(total ,categoryList);






    }
}
