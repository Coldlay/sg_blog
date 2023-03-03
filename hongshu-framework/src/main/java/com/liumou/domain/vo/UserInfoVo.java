package com.liumou.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author coldplay
 * @create 2023-03-03 9:35
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}
