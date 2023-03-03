package com.liumou.controller;

import com.liumou.domain.ResponseResult;
import com.liumou.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coldplay
 * @create 2023-03-01 20:46
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getLink(){

        return linkService.getAllLink();
    }

}
