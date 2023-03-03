package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coldplay
 * @create 2023-02-28 17:54
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired(required = false)
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategory(){

        return categoryService.getCategory();
    }
}
