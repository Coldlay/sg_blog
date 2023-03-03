package com.liumou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.liumou.constant.Constant;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Article;
import com.liumou.domain.entity.Category;
import com.liumou.domain.vo.ArticleDetailVo;
import com.liumou.domain.vo.ArticleListVo;
import com.liumou.domain.vo.HotArticleVo;
import com.liumou.domain.vo.PageVo;
import com.liumou.mapper.ArticleMapper;
import com.liumou.service.ArticleService;

import com.liumou.service.CategoryService;
import com.liumou.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author coldplay
 * @create 2023-02-24 13:53
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    CategoryService categoryService;
    /**
     * 查询热门文章十篇，封装成ResponseResult返回
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {


        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Article::getStatus, Constant.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);//到这里即是将查询条件封装完毕


        Page<Article> page = new Page<>(1, 10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

//        List<HotArticleVo> hotArticleVos = new ArrayList<>();
//        for(Article a: articles){
//            HotArticleVo hotArticleVo = new HotArticleVo();
//            BeanUtils.copyProperties(a,hotArticleVo);
//            hotArticleVos.add(hotArticleVo);
//        }
        List<HotArticleVo> hotArticleVos1 = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVos1);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        //封装查询条件
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Objects.nonNull(categoryId)&&categoryId > 0,Article::getCategoryId,categoryId)
                .eq(Article::getStatus,Constant.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop);
        //分页功能
        Page<Article> articlePage = new Page<>(pageNum,pageSize);
        //查询
        page(articlePage,wrapper);

        List<Article> articles = articlePage.getRecords();


        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, articlePage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult articleDetail(Long id) {
        Article article = getById(id);

        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryService.getById(article.getCategoryId());
        articleDetailVo.setCategoryName(category.getName());

        return ResponseResult.okResult(articleDetailVo);
    }
}
