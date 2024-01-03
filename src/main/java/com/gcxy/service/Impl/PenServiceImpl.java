package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.Pen.AddPenDao;
import com.gcxy.dao.Pen.DeletePenDao;
import com.gcxy.dao.Pen.UpdatePenDao;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pasture;
import com.gcxy.entity.Pen;
import com.gcxy.mapper.PenMapper;
import com.gcxy.service.PenService;
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
 * 圈舍管理表 服务实现类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Service
public class PenServiceImpl extends ServiceImpl<PenMapper, Pen> implements PenService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PenMapper penMapper;

    @Override
    public R selectPagePen(MyPage<Pen> myPage) {
        QueryWrapper<Pen> queryWrapper=new QueryWrapper<>();
        Page<Pen> page=new Page<>(myPage.getPageNumber(), myPage.getPageSize());
        if(myPage.getData()!=null){
            queryWrapper
                    .select("pen_Id","ranch_id","pasture_name","pen_name","amount_livestock","pen.status","pen.create_time","pen.update_time")
                    .like(myPage.getData().getPastureName()!=null,"pasture_name",myPage.getData().getPastureName())
                    .like(myPage.getData().getPenName()!=null,"pen_name",myPage.getData().getPenName())
                    .eq(myPage.getData().getStatus()!=null,"pasture.status",myPage.getData().getStatus());
        }
        penMapper.selectPage(page,queryWrapper);
        List<Pen> records=page.getRecords();
        Map data=new HashMap<>();
        data.put("data",records);
        return R.Success(data);
    }

    /**
     * 新增圈舍
     * @param addPenDao
     * @return
     */
    @Override
    public boolean addPen(AddPenDao addPenDao) {
//        System.out.println("进入pen服务类");
//        System.out.println(addPenDao.getRanchId());
//        System.out.println(addPenDao.getPenName());
        Pen pen=penMapper.getByDao(addPenDao.getRanchId(),addPenDao.getPenName());
        if (pen==null){
            Pen pen1= Pen.builder()
                    .ranchId(addPenDao.getRanchId())
                    .penName(addPenDao.getPenName())
                    .build();
            int count=penMapper.insertPen(pen1);
            return count>0;
        }
        return false;
    }

    /**
     * 修改圈舍
     * @param updatePenDao
     * @return
     */
    @Override
    public boolean updatePen(UpdatePenDao updatePenDao) {
        Pen pen=penMapper.getByPenId(updatePenDao.getPenId());
        if (pen!=null){
            Pen pen1=Pen.builder()
                    .penId(updatePenDao.getPenId())
                    .penName(updatePenDao.getPenName())
                    .build();
            int count = penMapper.updatePen(pen1);
            return count>0;
        }
        return false;
    }

    /**
     * 停(启)用圈舍
     * @param deletePenDao
     * @return
     */
    @Override
    public boolean deletePen(DeletePenDao deletePenDao) {
        Pen pen=penMapper.getByPenId(deletePenDao.getPenId());
        if (pen!=null){
            Pen pen1=Pen.builder()
                    .penId(deletePenDao.getPenId())
                    .status(deletePenDao.getStatus())
                    .build();
            int count= penMapper.deletePen(pen1);
            return count>0;
        }
        return false;
    }
}
