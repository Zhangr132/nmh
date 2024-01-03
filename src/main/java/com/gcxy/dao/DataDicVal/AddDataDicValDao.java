package com.gcxy.dao.DataDicVal;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangr132
 * @Date 2023/12/22 17:19
 * @注释  字典值保存类   添加
 */
@Data
public class AddDataDicValDao {
    private Integer id;

    @NotNull
    private Integer dicId;

    @NotEmpty
    private String valValue;
}
