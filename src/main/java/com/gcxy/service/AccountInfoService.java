package com.gcxy.service;

import com.gcxy.config.R;
import com.gcxy.dao.DeleteDao;
import com.gcxy.dao.LoginDao;
import com.gcxy.dao.RegisterDao;
import com.gcxy.dao.UpdateDao;
import com.gcxy.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcxy.entity.MyPage;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-12
 */
public interface AccountInfoService extends IService<AccountInfo> {
    List<AccountInfo> getAll();

    /*AccountInfo getAccount(String account);
    //新增
    int addAccount(AccountInfo accountInfo);
    //修改
    int updateAccount(AccountInfo accountInfo);*/

    boolean register(RegisterDao registerDao) throws Exception;

    String login(LoginDao loginDao) throws Exception;

    boolean update(UpdateDao updateDao) throws Exception;

    R pageAccount(MyPage<AccountInfo> myPage);

    boolean delete(DeleteDao deleteDao);


}