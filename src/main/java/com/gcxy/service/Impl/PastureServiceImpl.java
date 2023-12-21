package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.Pasture.AddPastureDao;
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
     * @param myPage
     * @return
     */
    @Override
    public R selectPasture(MyPage<Pasture> myPage) {
        QueryWrapper<Pasture> queryWrapper=new QueryWrapper<>();
        //将pageSize和pageNumber放入Page中
        Page<Pasture> page=new Page<>(myPage.getPageNumber(),myPage.getPageSize());
        if(myPage.getData()!=null){
            queryWrapper
                    .select("pasture_Id","pasture_name","field_scale","feed_scale","province","city","district","longitude","latitude","addr_detail","val_id","val_value","amo_livestock","pasture.status","pasture.create_time","pasture.update_time")
                    .like(myPage.getData().getPastureName()!=null,"pasture_name",myPage.getData().getPastureName())
                    .eq(myPage.getData().getStatus()!=null,"pasture.status",myPage.getData().getStatus());
        }
        pastureMapper.selectPage(page,queryWrapper);
        List<Pasture> records=page.getRecords();
        Map data=new HashMap<>();
        data.put("data",records);
        return R.Success(data);
    }

    @Override
    public boolean addPasture(AddPastureDao addPastureDao) {
        return false;
    }
}
