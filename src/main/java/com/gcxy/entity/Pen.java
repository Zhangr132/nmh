package com.gcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 圈舍管理表
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("pasture LEFT JOIN data_dic_val on pasture.val_id = data_dic_val.id")
@ApiModel(value = "Pen对象", description = "圈舍管理表")
public class Pen implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("圈舍编号")
    @TableId(value = "pen_Id", type = IdType.AUTO)
    private Integer penId;

    @ApiModelProperty("牧场编号(关联牧场)")
    private Integer ranchId;
    @ApiModelProperty("牧场名称（牧场表字段）")
    private String pastureName;

    @ApiModelProperty("圈舍名称(同牧场下唯一)")
    private String penName;

    @ApiModelProperty("存栏量")
    private Integer amountLivestock;

    @ApiModelProperty("圈舍状态(0:停用，1:启用)")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
