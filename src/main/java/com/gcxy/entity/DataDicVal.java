package com.gcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据字典值表
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Getter
@Setter
@TableName("data_dic_val")
@ApiModel(value = "DataDicVal对象", description = "数据字典值表")
public class DataDicVal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典值表")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("字典编号（关联字典）")
    private Integer dicId;

    @ApiModelProperty("字典值取值")
    private String valValue;

    @ApiModelProperty("字典值状态（1：启用，0：停用）")
    private Boolean status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
