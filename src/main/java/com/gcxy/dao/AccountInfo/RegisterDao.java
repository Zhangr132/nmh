package com.gcxy.dao.AccountInfo;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 用户保存类    注册
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-24
 */
@Data
public class RegisterDao implements Serializable {
    /*@NotEmpty(message = "登录账号不能为空")
    @Length(min = 5, max = 11, message = "账号长度为5-11")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String account;*/

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 11, message = "用户名长度为5-11")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String accName;

    /*@NotEmpty(message = "登录密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度为6-16")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "密码只能是数字和字母")*/
//    private String password;

    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号长度为11")
    private String accPhone;

    private Boolean status;
}