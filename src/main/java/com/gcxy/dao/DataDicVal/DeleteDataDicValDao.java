package com.gcxy.dao.DataDicVal;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author zhangr132
 * @Date 2023/12/22 17:19
 * @注释
 */
@Data
public class DeleteDataDicValDao {
    @NotNull
    private Integer id;
    private Integer status;
}
