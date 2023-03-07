package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Comment;
import com.liumou.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @author coldplay
 * @create 2023-03-07 10:28
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping("/comment/commentList")
    public ResponseResult getAllComment(Long articleId,Integer pageNum,Integer pageSize){

        return commentService.getComments(articleId,pageNum,pageSize);
    }

    @RequestMapping("/comment")
    public ResponseResult addComment(@RequestBody Comment comment){


        return commentService.addComment(comment);
    }
}
