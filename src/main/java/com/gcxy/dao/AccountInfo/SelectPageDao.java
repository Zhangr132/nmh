package com.gcxy.dao.AccountInfo;

import com.gcxy.entity.AccountInfo;
import lombok.Data;

/**
 * <p>
 * 用户保存类    分页查询
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-24
 */
@Data
public class SelectPageDao extends AccountInfo {
    private Integer pageSize=10;
    private Integer pageNumber=1;
}
