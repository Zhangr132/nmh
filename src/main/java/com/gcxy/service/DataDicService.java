package com.gcxy.service;

import com.gcxy.dao.DataDic.AddDataDicDao;
import com.gcxy.entity.DataDic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
public interface DataDicService extends IService<DataDic> {

    boolean addDataDic(AddDataDicDao addDataDicDao);
}
