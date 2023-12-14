package com.gcxy.service;

import com.gcxy.dao.LoginDao;
import com.gcxy.dao.RegisterDao;
import com.gcxy.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;

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

    AccountInfo getAccount(String account);
    //新增
    int addAccount(AccountInfo accountInfo);
    //修改
    int updateAccount(AccountInfo accountInfo);

    boolean register(RegisterDao registerDao) throws Exception;

    String login(LoginDao loginDao) throws Exception;
}
