package com.gcxy.service.Impl;

import com.gcxy.dao.DataDic.AddDataDicDao;
import com.gcxy.entity.DataDic;
import com.gcxy.mapper.DataDicMapper;
import com.gcxy.service.DataDicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Service
public class DataDicServiceImpl extends ServiceImpl<DataDicMapper, DataDic> implements DataDicService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    DataDicMapper dataDicMapper;

    /**
     * 新增字典名称
     * @param addDataDicDao
     * @return
     */
    @Override
    public boolean addDataDic(AddDataDicDao addDataDicDao) {
        DataDic dataDic=dataDicMapper.getDataDicById(addDataDicDao.getId());
        if (dataDic==null){
            DataDic dataDic1=DataDic.builder()
                    .dicName(addDataDicDao.getDicName())
                    .build();
            int count=dataDicMapper.insertDataDic(dataDic1);
            return count>0;
        }
        return false;
    }
}
