package com.gcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 牧场管理表
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
@ApiModel(value = "Pasture对象", description = "牧场管理表")
public class Pasture implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("牧场编号")
    @TableId(value = "pasture_Id", type = IdType.AUTO)
    private Integer pastureId;

    @ApiModelProperty("牧场名称")
    private String pastureName;

    @ApiModelProperty("场地规模(100-999999)")
    private Integer fieldScale;

    @ApiModelProperty("养殖规模(0-999999)")
    private Integer feedScale;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String district;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("详细地址")
    private String addrDetail;

    @ApiModelProperty("字典值编号（关联具体牲畜品种）")
    private Integer valId;  //可有可无

    @ApiModelProperty("牲畜品种（字典值字段）")
    private String valValue;

    @ApiModelProperty("存栏量")
    private Integer amoLivestock;

    @ApiModelProperty("牧场状态（1：启用，0：停用）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
