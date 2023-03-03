package com.liumou.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 友链(Link)实体类
 *
 * @author makejava
 * @since 2023-03-01 20:43:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_link")
public class Link implements Serializable {
    private static final long serialVersionUID = 599647633147941571L;

    @TableId
    private Long id;
    
    private String name;
    
    private String logo;
    
    private String description;
    /**
     * 网站地址
     */
    private String address;
    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    private String status;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;



}
