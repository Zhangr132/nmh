package com.gcxy.dao.AccountInfo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * @Author zhangr132
 * @Date 2023/12/19 16:03
 * @注释  用户保存类   删除
 */
@Data
public class DeleteDao {
    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 5, max = 11, message = "账号长度为5-11")
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String account;

    private Boolean status;
}
