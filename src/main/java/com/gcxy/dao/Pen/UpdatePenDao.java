package com.gcxy.dao.Pen;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangr132
 * @Date 2023/12/22 14:24
 * @注释  圈舍保存类    修改
 */
@Data
public class UpdatePenDao {
    @NotNull
    private Integer penId;

    @NotEmpty
    @Length(max=50,message ="圈舍名称长度最长50" )
    private String penName;
}
