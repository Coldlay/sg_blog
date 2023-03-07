package com.liumou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Comment;

/**
 * @author coldplay
 * @create 2023-03-07 10:30
 */
public interface CommentService extends IService<Comment> {

    ResponseResult getComments(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
