package com.liumou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liumou.domain.entity.Article;
import com.liumou.domain.vo.ArticleDetailVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author coldplay
 * @create 2023-02-24 13:52
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 多表联查实例
     * @return
     */
    List<ArticleDetailVo> getArticleListF();


}
