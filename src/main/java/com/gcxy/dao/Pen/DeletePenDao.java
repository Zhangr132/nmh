package com.gcxy.dao.Pen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangr132
 * @Date 2023/12/22 14:24
 * @注释
 */
@Data
public class DeletePenDao {
    @NotNull
    private Integer penId;

    private Integer status;
}
