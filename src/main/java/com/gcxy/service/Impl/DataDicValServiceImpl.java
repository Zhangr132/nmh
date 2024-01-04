package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.DataDicVal.AddDataDicValDao;
import com.gcxy.dao.DataDicVal.DataDicValPageDao;
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
     * @param dataDicValPageDao
     * @return
     */
    @Override
    public R selectDicVal(DataDicValPageDao dataDicValPageDao) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        //将pageSize和pageNumber放入Page中
        Page<DataDicVal> page=new Page<>(dataDicValPageDao.getPageNumber(),dataDicValPageDao.getPageSize());
        queryWrapper
                    .select("data_dic_val.Id","dic_id","dic_name","val_value","data_dic_val.status","data_dic_val.create_time",
                            "data_dic_val.update_time")
                    .like(dataDicValPageDao.getValValue()!=null,"val_value",dataDicValPageDao.getValValue())
                    .eq(dataDicValPageDao.getStatus()!=null,"data_dic_val.status",dataDicValPageDao.getStatus());

        IPage<DataDicVal> dataDicValIPage=dataDicValMapper.selectPage(page,queryWrapper);
        List<DataDicVal> records=dataDicValIPage.getRecords();
        Map responseData=new HashMap<>();
        responseData.put("data", records);
        responseData.put("total", dataDicValIPage.getTotal()); // 总记录数
        responseData.put("size", dataDicValIPage.getSize()); // 每页显示数量
        responseData.put("current", dataDicValIPage.getCurrent()); // 当前页码
//        responseData.put("orders", dataDicValIPage.orders()); // 排序信息
//        responseData.put("optimizeCountSql", dataDicValIPage.optimizeCountSql()); // 是否优化count语句
        responseData.put("pages", dataDicValIPage.getPages()); // 总页数
        return R.Success(responseData);
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
