//package com.sky.service.impl;
//
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.sky.context.BaseContext;
//import com.sky.dto.CategoryDTO;
//import com.sky.dto.CategoryPageQueryDTO;
//import com.sky.entity.Category;
//import com.sky.mapper.CategoryMapper;
//import com.sky.result.PageResult;
//import com.sky.service.CategoryService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.awt.geom.PathIterator;
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@Slf4j
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    @Autowired
//    private CategoryMapper categoryMapper;
//
//    @Override
//    public PageResult SreachCategory(CategoryPageQueryDTO categoryPageQueryDTO) {
//
//        //pagehapler分页插件进行分页查询
//        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
//        Page<Category> categories = categoryMapper.sreachCategroy(categoryPageQueryDTO);
//        //获取总条数
//        long total = categories.getTotal();
//        //获取所有categroy数据
//        List<Category> categoryList = categories.getResult();
//
//        return  new PageResult(total ,categoryList);
//    }
//
//    //新增菜品分类
//    @Override
//    public void addCategory(CategoryDTO categoryDTO) {
//
//        Category category = new Category();
//        category.setName(categoryDTO.getName());
//        category.setSort(categoryDTO.getSort());
//        category.setType(categoryDTO.getType());
//        category.setStatus(0);
////        category.setCreateTime(LocalDateTime.now());
////        category.setCreateTime(LocalDateTime.now());
////        category.setCreateUser(BaseContext.getCurrentId());
////        category.setUpdateUser(BaseContext.getCurrentId());
//
//        categoryMapper.addCategory(category);
//
//    }
//
//    @Override
//    public void delCategory(long id) {
//        categoryMapper.delCategory(id);
//    }
//
//    @Override
//    public void updateCateInfo(long id, int status) {
//        Category category = Category.builder()
//                .id(id)
//                .status(status)
////                .updateTime(LocalDateTime.now())
////                .updateUser(BaseContext.getCurrentId())
//                .build();
//
//        categoryMapper.updateCateInfo(category);
//    }
//
//    @Override
//    public void updateCateInfo(CategoryDTO categoryDTO) {
//        Category categoryinfo = Category.builder()
//                .id(categoryDTO.getId())
//                .type(categoryDTO.getType())
//                .name(categoryDTO.getName())
//                .sort(categoryDTO.getSort())
////                .updateTime(LocalDateTime.now())
////                .updateUser(BaseContext.getCurrentId())
//                .build();
//
//        categoryMapper.updateCateInfo(categoryinfo);
//    }
//
//    @Override
//    public PageResult SreachCatebyType(CategoryPageQueryDTO categoryPageQueryDTO) {
//
//        //修改page
//        Integer page = (categoryPageQueryDTO.getPage()-1)*categoryPageQueryDTO.getPageSize();
//        categoryPageQueryDTO.setPage(page);
//        List<Category> catelist = categoryMapper.sreachCatebyType(categoryPageQueryDTO);
//        PageResult pageResult = new PageResult();
//        pageResult.setRecords(catelist);
//        log.info("查询后参数:{}",catelist);
//        return pageResult;
//
//    }
//}
