package com.gcxy.dao.Pen;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangr132
 * @Date 2023/12/22 14:23
 * @注释  圈舍保存类    添加
 */
@Data
public class AddPenDao {
    @NotNull
    private Integer ranchId;

    @NotEmpty
    @Length(max=50,message ="圈舍名称长度最长50" )
    private String penName;
}
