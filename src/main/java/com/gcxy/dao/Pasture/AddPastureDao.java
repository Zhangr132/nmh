package com.gcxy.dao.Pasture;



import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * @Author zhangr132
 * @Date 2023/12/21 11:34
 * @注释
 */
public class AddPastureDao {
    private Integer pastureId;
    @NotEmpty(message = "牧场名称不能为空")
    @Length(max = 100,message = "牧场名称长度最长为100")
    private String pastureName;
    @NotEmpty(message = "牧场规模不能为空")
    @Length(min = 100,max = 999999,message = "牧场规模为100-999999平方米")
    private String fieldScale;
    @NotEmpty(message = "养殖规模不能为空")
    @Length(max = 999999,message = "养殖规模不能超过999999")
    private String feedScale;
    @NotEmpty(message = "省不能为空")
    private String province;
    @NotEmpty(message = "市不能为空")
    private String city;
    @NotEmpty(message = "区不能为空")
    private String district;

    private BigDecimal longitude;

    private BigDecimal latitude;
    @NotEmpty(message = "详细地址不能为空")
    private String addrDetail;

    private Integer valId;
}
