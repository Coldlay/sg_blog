package com.liumou.job;

import com.liumou.domain.entity.Article;
import com.liumou.service.ArticleService;
import com.liumou.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author coldplay
 * @create 2023-03-08 21:38
 */
@Component
public class UpdateViewCountJob {


    @Autowired
    RedisCache redisCache;

    @Autowired
    ArticleService articleService;

    @Scheduled(cron = "9 * * * 6 ?")
    public void updateViewCount(){
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");

        List<Article> collect = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        articleService.updateBatchById(collect);
    }
}
