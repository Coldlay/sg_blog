package com.liumou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liumou.domain.ResponseResult;
import com.liumou.domain.entity.Link;
import org.springframework.stereotype.Service;

/**
 * @author coldplay
 * @create 2023-03-01 20:44
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
