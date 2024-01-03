package com.gcxy.service;

import com.gcxy.config.R;
import com.gcxy.dao.AccountInfo.*;
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


    boolean register(RegisterDao registerDao) throws Exception;

    String login(LoginDao loginDao) throws Exception;

    boolean updateAccount(UpdateDao updateDao) throws Exception;

    R selectAccount(SelectPageDao selectPageDao);

    boolean deleteAccount(DeleteDao deleteDao);


}