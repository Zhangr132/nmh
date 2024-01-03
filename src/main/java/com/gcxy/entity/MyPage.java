package com.gcxy.entity;

import lombok.Data;

/**
 * @Author zhangr132
 * @Date 2023/12/17 13:56
 * @注释  分页查询实体类
 */

@Data
public class MyPage<T> {
    private Integer pageSize=10;
    private Integer pageNumber=1;
    private T data;
}
