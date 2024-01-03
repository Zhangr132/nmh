package com.gcxy.dao.Pasture;

import com.gcxy.entity.Pasture;
import lombok.Data;

/**
 * @Author zhangr132
 * @Date 2024/1/3 18:57
 * @注释
 */
@Data
public class PasturePageDao extends Pasture {
    private Integer pageSize=10;
    private Integer pageNumber=1;
}
