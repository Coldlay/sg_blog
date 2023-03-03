package com.liumou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liumou.constant.Constant;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Article;
import com.liumou.domain.entity.Category;
import com.liumou.domain.vo.CategoryVo;
import com.liumou.mapper.CategoryMapper;
import com.liumou.service.ArticleService;
import com.liumou.service.CategoryService;
import com.liumou.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author coldplay
 * @create 2023-02-28 17:49
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    ArticleService articleService;


    @Override
    public ResponseResult getCategory() {


        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(articleLambdaQueryWrapper);

        Set<Long> categoryIds = list.stream().map(Article::getCategoryId).collect(Collectors.toSet());

        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> Constant.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);


        return ResponseResult.okResult(categoryVos);
    }
}
