package com.gcxy.dao.DataDicVal;

import com.gcxy.entity.DataDicVal;
import lombok.Data;

/**
 * @Author zhangr132
 * @Date 2024/1/3 19:06
 * @注释
 */
@Data
public class DataDicValPageDao extends DataDicVal {
    private Integer pageSize=10;
    private Integer pageNumber=1;
}
