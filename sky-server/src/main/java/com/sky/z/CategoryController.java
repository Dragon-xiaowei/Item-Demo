//package com.sky.controller.admin;
//
//
//import com.sky.dto.CategoryDTO;
//import com.sky.dto.CategoryPageQueryDTO;
//import com.sky.entity.Category;
//import com.sky.result.PageResult;
//import com.sky.result.Result;
//import com.sky.service.CategoryService;
//import lombok.extern.slf4j.Slf4j;
//import net.bytebuddy.asm.Advice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/admin/category")
//@Slf4j
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//
//    //分页查询
//    @GetMapping("/page")
//    public Result<PageResult> sreachCategory(CategoryPageQueryDTO categoryPageQueryDTO){
//        log.info("请求参数:{}",categoryPageQueryDTO);
//       PageResult pageResult =  categoryService.SreachCategory(categoryPageQueryDTO);
//
//        return Result.success(pageResult);
//    }
//
//
//    //新增分类
//    @PostMapping
//    public Result AddCategory(@RequestBody CategoryDTO categoryDTO){
//        log.info("{}",categoryDTO);
//
//        categoryService.addCategory(categoryDTO);
//
//        return  Result.success();
//    }
//
//
//    //删除分类
//    @DeleteMapping
//    public Result delCategory(long id){
//        log.info("删除的id:{}",id);
//        categoryService.delCategory(id);
//        return  Result.success();
//    }
//
//
//    //启用禁用分类
//    @PostMapping("/status/{status}")
//    public Result updateCateStatus (@PathVariable int status,@RequestParam long id ){
//        log.info("需修改的id和状态{},{}",id,status);
//        categoryService.updateCateInfo(id,status);
//        return  Result.success();
//    }
//
//
//    //修改菜品分类信息
//    @PutMapping
//    public Result updateCateInfo(@RequestBody CategoryDTO categoryDTO){
//        log.info("需要修改的菜品分类为:{}",categoryDTO);
//        categoryService.updateCateInfo(categoryDTO);
//        return  Result.success();
//    }
//
//
//    //根据类型查询分类
//    @GetMapping("/lsit")
//    public Result SreachCatebyType(@RequestParam CategoryPageQueryDTO categoryPageQueryDTO){
//
//
//        log.info("{}",categoryPageQueryDTO);
//       // PageResult pageResult = categoryService.SreachCatebyType(categoryPageQueryDTO);
//
//        return  Result.success();
//
//    }
//
//
//
//
//}
