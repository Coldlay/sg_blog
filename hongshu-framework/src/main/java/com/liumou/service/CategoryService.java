package com.liumou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Category;

/**
 * @author coldplay
 * @create 2023-02-28 17:49
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategory();
}
