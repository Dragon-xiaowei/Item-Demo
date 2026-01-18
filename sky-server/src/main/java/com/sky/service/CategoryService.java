package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    PageResult SreachCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categoryDTO);

    void delCategory(long id);

    void updateCateInfo(long id, int status) ;

    void updateCateInfo(CategoryDTO categoryDTO);

    PageResult SreachCatebyType(CategoryPageQueryDTO categoryPageQueryDTO);
}
