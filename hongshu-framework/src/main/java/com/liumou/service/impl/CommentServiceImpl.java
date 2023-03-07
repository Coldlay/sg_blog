package com.liumou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Comment;
import com.liumou.domain.vo.CommentVo;
import com.liumou.domain.vo.PageVo;
import com.liumou.mapper.CommentMapper;
import com.liumou.mapper.UserMapper;
import com.liumou.service.CommentService;

import com.liumou.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author coldplay
 * @create 2023-03-07 10:31
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserMapper userMapper;


    //TODO 能不能换一种方式，使用多表联查的方式实现，同时也要实现分页功能
    @Override
    public ResponseResult getComments(Long articleId, Integer pageNum, Integer pageSize) {
        //封装查询条件
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();

        //因为是首先要查询一篇文章的根评论，及rootId为-1的评论，后续在查询其下的子评论
        queryWrapper.eq(Comment::getArticleId,articleId)
        .eq(Comment::getRootId,-1);


        //封装分页条件
        Page<Comment> commentPage = new Page<>(pageNum, pageSize);

        //查询
        page(commentPage,queryWrapper);//将查询结果封装到commentPage对象中

        List<CommentVo> comments = toCommentVoList(commentPage.getRecords());//commentPage.getRecords方法是返回在数据库中查到的内容

        for (CommentVo c:comments){
            c.setChildren(getChildren(c.getId()));

        }

        return ResponseResult.okResult(new PageVo(comments,commentPage.getTotal()));//最后记得把其封装成pageVo类型
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> toCommentVoList (List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);


        //为了将ToCommentUserId，Username填充上
        for(CommentVo c:commentVos){
            if(c.getToCommentId() != -1){
                c.setToCommentUserName(userMapper.selectById(c.getToCommentUserId()).getUserName());
            }

            c.setUsername(userMapper.selectById(c.getCreateBy()).getUserName());
        }

        return commentVos;
    }

    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id)
                .orderByAsc(Comment::getCreateTime);

        List<Comment> list = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(list);

        return commentVos;
    }
}
