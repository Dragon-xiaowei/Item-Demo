package com.sky.controller.admin;


import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //分页查询
    ;@GetMapping("/page")
    public Result<PageResult> sreachCategory(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("请求参数:{}",categoryPageQueryDTO);
       PageResult pageResult =  categoryService.SreachCategory(categoryPageQueryDTO);

        return Result.success(pageResult);
    }

}
