package com.gcxy.dao.Pen;

import com.gcxy.entity.Pen;
import lombok.Data;

/**
 * @Author zhangr132
 * @Date 2024/1/3 18:55
 * @注释
 */
@Data
public class PenPageDao extends Pen {
    private Integer pageSize=10;
    private Integer pageNumber=1;
}
