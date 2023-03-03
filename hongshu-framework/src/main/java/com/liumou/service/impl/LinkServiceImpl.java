package com.liumou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liumou.constant.Constant;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Link;
import com.liumou.domain.vo.LinkVo;
import com.liumou.mapper.LinkMapper;
import com.liumou.service.LinkService;
import com.liumou.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author coldplay
 * @create 2023-03-01 20:45
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService{


    @Override
    public ResponseResult getAllLink() {

        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Link::getStatus, Constant.STATUS_NORMAL_NUM);

        List<Link> list = list(wrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}
