package com.gcxy.dao.AccountInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 用户保存类    登录
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-24
 */
@Data
public class LoginDao implements Serializable {
    @NotEmpty(message = "登录名不能为空")
    @Length(min = 5, max = 11, message = "账号长度为5-11")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String accName;

    @NotEmpty(message = "登录密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为6-16")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "密码只能是数字和字母")
    private String password;
}
