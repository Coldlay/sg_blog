package com.liumou.runner;

import com.liumou.domain.entity.Article;
import com.liumou.mapper.ArticleMapper;
import com.liumou.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author coldplay
 * @create 2023-03-08 21:14
 */
@Component
public class ViewCountRunner implements CommandLineRunner {


    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {

        //查询博客信息
        List<Article> articles = articleMapper.selectList(null);

        Map<String,Integer> viewCountMap =  articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(),article ->article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);

    }
}
