package com.gcxy.service;

import com.gcxy.config.R;
import com.gcxy.dao.Pen.AddPenDao;
import com.gcxy.dao.Pen.DeletePenDao;
import com.gcxy.dao.Pen.PenPageDao;
import com.gcxy.dao.Pen.UpdatePenDao;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 圈舍管理表 服务类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
public interface PenService extends IService<Pen> {

    R selectPagePen(PenPageDao penPageDao);

    boolean addPen(AddPenDao addPenDao);

    boolean updatePen(UpdatePenDao updatePenDao);

    boolean deletePen(DeletePenDao deletePenDao);
}
