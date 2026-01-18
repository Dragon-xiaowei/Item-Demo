package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CategoryMapper {


    //分页查询菜品分类
    Page<Category> sreachCategroy(CategoryPageQueryDTO categoryPageQueryDTO);


    //新增分类
    @Insert("insert into category (type,name,sort,status,create_time,update_time,create_user,update_user)" +
            " values (#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addCategory(Category category);

    //删除菜品分类
    @Delete("delete from category where  id = #{id}")
    void delCategory(long id);



    //修改菜品状态和信息
    void updateCateInfo(Category category);


    //根据类型查询菜品分类信息
    List<Category> sreachCatebyType(CategoryPageQueryDTO categoryPageQueryDTO);
}