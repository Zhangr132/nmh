package com.gcxy.service;

import com.gcxy.config.R;
import com.gcxy.dao.DataDicVal.AddDataDicValDao;
import com.gcxy.dao.DataDicVal.DataDicValPageDao;
import com.gcxy.dao.DataDicVal.DeleteDataDicValDao;
import com.gcxy.entity.DataDicVal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcxy.entity.MyPage;

/**
 * <p>
 * 数据字典值表 服务类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
public interface DataDicValService extends IService<DataDicVal> {

    R selectDicVal(DataDicValPageDao dataDicValPageDao);

    boolean addDicVal(AddDataDicValDao addDataDicValDao);

    boolean stopDicVal(DeleteDataDicValDao deleteDataDicValDao);
}
