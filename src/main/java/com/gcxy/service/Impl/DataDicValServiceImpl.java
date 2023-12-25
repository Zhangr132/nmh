package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.DataDicVal.AddDataDicValDao;
import com.gcxy.dao.DataDicVal.DeleteDataDicValDao;
import com.gcxy.entity.DataDicVal;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pasture;
import com.gcxy.mapper.DataDicValMapper;
import com.gcxy.service.DataDicValService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典值表 服务实现类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Service
public class DataDicValServiceImpl extends ServiceImpl<DataDicValMapper, DataDicVal> implements DataDicValService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    DataDicValMapper dataDicValMapper;

    /**
     * 分页查询
     * @param myPage
     * @return
     */
    @Override
    public R selectDicVal(MyPage<DataDicVal> myPage) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        //将pageSize和pageNumber放入Page中
        Page<DataDicVal> page=new Page<>(myPage.getPageNumber(),myPage.getPageSize());
        if (myPage.getData()!=null){
            queryWrapper
                    .select("data_dic_val.Id","dic_id","dic_name","val_value","data_dic_val.status","data_dic_val.create_time","data_dic_val.update_time")
                    .like(myPage.getData().getValValue()!=null,"val_value",myPage.getData().getValValue())
                    .eq(myPage.getData().getStatus()!=null,"data_dic_val.status",myPage.getData().getStatus());
        }
        dataDicValMapper.selectPage(page,queryWrapper);
        List<DataDicVal> records=page.getRecords();
        Map data=new HashMap<>();
        data.put("data",records);
        return R.Success(data);
    }

    /**
     * 新增
     * @param addDataDicValDao
     * @return
     */
    @Override
    public boolean addDicVal(AddDataDicValDao addDataDicValDao) {
        DataDicVal dataDicVal=dataDicValMapper.getById(addDataDicValDao.getId());
        if (dataDicVal==null){
            DataDicVal dataDicVal1=DataDicVal.builder()
                    .id(addDataDicValDao.getId())
                    .dicId(addDataDicValDao.getDicId())
                    .valValue(addDataDicValDao.getValValue())
                    .build();
            int count=dataDicValMapper.insertDataDicVal(dataDicVal1);
            return count>0;
        }
        return false;
    }

    /**
     * 修改状态
     * @param deleteDataDicValDao
     * @return
     */
    @Override
    public boolean stopDicVal(DeleteDataDicValDao deleteDataDicValDao) {
        DataDicVal dataDicVal=dataDicValMapper.getById(deleteDataDicValDao.getId());
        if (dataDicVal!=null){
            DataDicVal dataDicVal1=DataDicVal.builder()
                    .id(deleteDataDicValDao.getId())
                    .status(deleteDataDicValDao.getStatus())
                    .build();
            int count=dataDicValMapper.deleteDataDicVal(dataDicVal1);
            return count>0;
        }
        return false;
    }
}
