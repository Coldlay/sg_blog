package com.liumou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Article;

import java.util.List;

/**
 * @author coldplay
 * @create 2023-02-24 13:53
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult articleDetail(Long id);
}
