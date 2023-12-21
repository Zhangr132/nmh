package com.gcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Data
@TableName("data_dic")
@ApiModel(value = "DataDic对象", description = "数据字典")
public class DataDic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典编号")
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("字典名称")
    private String dicName;

    @ApiModelProperty("字典状态（0：未发布，1：已发布，2：已停用）")
    private String dicStatus;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
