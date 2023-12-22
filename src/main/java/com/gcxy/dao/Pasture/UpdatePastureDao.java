package com.gcxy.dao.Pasture;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author zhangr132
 * @Date 2023/12/22 9:51
 * @注释
 */
@Data
public class UpdatePastureDao {
    private Integer pastureId;

    @NotEmpty
    @Length(max = 100,message = "牧场名称长度最长为100")
    private String pastureName;

    //    @NotEmpty(message = "牧场规模不能为空")
    @NotNull
    @Min(value = 100,message="牧场规模不能少于100")
    @Max(value = 999999,message = "牧场规模不能大于999999")
    private Integer fieldScale;


    //    @NotEmpty(message = "养殖规模不能为空")
    @NotNull
    @Min(value = 0)
    @Max(value = 999999,message = "养殖规模不能超过999999")
    private Integer feedScale;

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
