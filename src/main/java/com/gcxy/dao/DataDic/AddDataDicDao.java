package com.gcxy.dao.DataDic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author zhangr132
 * @Date 2023/12/22 16:50
 * @注释
 */
@Data
public class AddDataDicDao {
    private Integer id;

    @NotEmpty(message = "输入不能为空")
    private String dicName;
}
