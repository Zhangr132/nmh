package com.gcxy.dao.AccountInfo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户保存类    修改
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-25
 */
@Data
public class UpdateDao {

    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 5, max = 11, message = "账号长度为5-11")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String account;

    @NotEmpty(message = "用户名不能为空")
    @Length(max = 10, message = "登录名最长为10")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String accName;

    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号长度为11")
    private String accPhone;

    private Boolean status;
}
