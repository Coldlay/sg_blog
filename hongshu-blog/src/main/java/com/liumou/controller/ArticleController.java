package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Article;
import com.liumou.domain.vo.ArticleDetailVo;
import com.liumou.mapper.ArticleMapper;
import com.liumou.service.ArticleService;
import com.liumou.service.CategoryService;
import com.liumou.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author coldplay
 * @create 2023-02-24 13:56
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    public List<Article> test(){
      return articleService.list();
    }

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult atricleList(Integer pageNum,Integer pageSize,Long categoryId){

        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult articleDetail(@PathVariable("id")Long id){

        return articleService.articleDetail(id);
    }

    @Autowired()
    ArticleMapper articleMapper;

    @GetMapping("/test")
    public ResponseResult testmapper(){

        List<ArticleDetailVo> articleListF = articleMapper.getArticleListF();
        return ResponseResult.okResult(articleListF);
    }

//    @Autowired
//    RedisCache redisCache;
//
//    @GetMapping("/testredis")
//    public ResponseResult testRedis(){
//        List<Object> k100 = redisCache.getCacheList("k100");
//
//        String k100 = redisCache.getCacheObject("k100");
//        System.out.println(k100);
//        return null;
//    }



}
