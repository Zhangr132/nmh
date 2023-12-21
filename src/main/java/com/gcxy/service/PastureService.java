package com.gcxy.service;

import com.gcxy.config.R;
import com.gcxy.dao.Pasture.AddPastureDao;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pasture;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 牧场管理表 服务类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
public interface PastureService extends IService<Pasture> {

    R selectPasture(MyPage<Pasture> myPage) ;


    boolean addPasture(AddPastureDao addPastureDao);
}
