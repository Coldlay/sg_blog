package com.liumou.service;

import com.liumou.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author coldplay
 * @create 2023-03-08 11:14
 */
public interface UploadService {
    ResponseResult upload(MultipartFile img);
}
