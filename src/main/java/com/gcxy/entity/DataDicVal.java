package com.gcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 数据字典值表
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("data_dic_val LEFT JOIN data_dic on data_dic_val.dic_id = data_dic.id")
@ApiModel(value = "DataDicVal对象", description = "数据字典值表")
public class DataDicVal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典值表")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("字典编号（关联字典）")
    private Integer dicId;
    @ApiModelProperty("字典名称（字典字段）")
    private String dicName;

    @ApiModelProperty("字典值取值")
    private String valValue;

    @ApiModelProperty("字典值状态（1：启用，0：停用）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
