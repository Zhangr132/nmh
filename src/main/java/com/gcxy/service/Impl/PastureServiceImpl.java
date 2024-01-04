package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.Pasture.AddPastureDao;
import com.gcxy.dao.Pasture.DeletePastureDao;
import com.gcxy.dao.Pasture.PasturePageDao;
import com.gcxy.dao.Pasture.UpdatePastureDao;
import com.gcxy.entity.AccountInfo;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pasture;
import com.gcxy.mapper.PastureMapper;
import com.gcxy.service.PastureService;
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
 * 牧场管理表 服务实现类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Service
public class PastureServiceImpl extends ServiceImpl<PastureMapper, Pasture> implements PastureService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PastureMapper pastureMapper;

    /**
     * 查询牧场信息
     * （牧场名称查询）
     * @param pasturePageDao
     * @return
     */
    @Override
    public R selectPasture(PasturePageDao pasturePageDao) {
        QueryWrapper<Pasture> queryWrapper=new QueryWrapper<>();
        //将pageSize和pageNumber放入Page中
        Page<Pasture> page=new Page<>(pasturePageDao.getPageNumber(),pasturePageDao.getPageSize());
        queryWrapper
                    .select("pasture_Id","pasture_name","field_scale","feed_scale","province","city","district","longitude","latitude","addr_detail","val_id","val_value","amo_livestock","pasture.status","pasture.create_time","pasture.update_time")
                    .like(pasturePageDao.getPastureName()!=null,"pasture_name",pasturePageDao.getPastureName())
                    .eq(pasturePageDao.getStatus()!=null,"pasture.status",pasturePageDao.getStatus());
        IPage<Pasture> pasturePage=pastureMapper.selectPage(page,queryWrapper);
        List<Pasture> records=pasturePage.getRecords();
        Map responseData=new HashMap<>();
        responseData.put("data", records);
        responseData.put("total", pasturePage.getTotal()); // 总记录数
        responseData.put("size", pasturePage.getSize()); // 每页显示数量
        responseData.put("current", pasturePage.getCurrent()); // 当前页码
//        responseData.put("orders", pasturePage.orders()); // 排序信息
//        responseData.put("optimizeCountSql", pasturePage.optimizeCountSql()); // 是否优化count语句
        responseData.put("pages", pasturePage.getPages()); // 总页数
        return R.Success(responseData);
    }

    /**
     * 新增牧场
     * @param addPastureDao
     * @return
     */
    @Override
    public boolean addPasture(AddPastureDao addPastureDao) {
        System.out.println("进入service");
        Pasture pasture=pastureMapper.getByPastureName(addPastureDao.getPastureName());
        if (pasture==null){
            System.out.println("准备新增");
            System.out.println(addPastureDao.getPastureName());
            Pasture pasture1=Pasture.builder()
                    .pastureName(addPastureDao.getPastureName())
                    .fieldScale(addPastureDao.getFieldScale())
                    .feedScale(addPastureDao.getFeedScale())
                    .province(addPastureDao.getProvince())
                    .city(addPastureDao.getCity())
                    .district(addPastureDao.getDistrict())
                    .longitude(addPastureDao.getLongitude())
                    .latitude(addPastureDao.getLatitude())
                    .addrDetail(addPastureDao.getAddrDetail())
                    .valId(addPastureDao.getValId())
                    .build();
            System.out.println("18265656565656556656565");
            int count= pastureMapper.insertPasture(pasture1);
            System.out.println("进入controler");
            return count>0;
        }
        return false;
    }

    /**
     * 修改牧场
     * @param updatePastureDao
     * @return
     */
    @Override
    public boolean updatePasture(UpdatePastureDao updatePastureDao) {
        Pasture pasture=pastureMapper.getByPastureName(updatePastureDao.getPastureName());
        if (pasture!=null){
            Pasture pasture1=Pasture.builder()
                    .pastureId(pasture.getPastureId())
                    .pastureName(updatePastureDao.getPastureName())
                    .fieldScale(updatePastureDao.getFieldScale())
                    .feedScale(updatePastureDao.getFeedScale())
                    .province(updatePastureDao.getProvince())
                    .city(updatePastureDao.getCity())
                    .district(updatePastureDao.getDistrict())
                    .longitude(updatePastureDao.getLongitude())
                    .latitude(updatePastureDao.getLatitude())
                    .addrDetail(updatePastureDao.getAddrDetail())
                    .valId(updatePastureDao.getValId())
                    .build();
            int count=pastureMapper.updatePasture(pasture1);
            return count>0;
        }
        return false;
    }

    /**
     * 停(启)用牧场
     * @param deletePastureDao
     * @return
     */
    @Override
    public boolean deletePasture(DeletePastureDao deletePastureDao) {
        Pasture pasture=pastureMapper.getByPastureId(deletePastureDao.getPastureId());
        if (pasture!=null){
            Pasture pasture1=Pasture.builder()
                    .pastureId(deletePastureDao.getPastureId())
                    .status(deletePastureDao.getStatus())
                    .build();
            int count=pastureMapper.deletePasture(pasture1);
            return count>0;
        }
        return false;
    }
}
