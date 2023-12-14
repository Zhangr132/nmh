package com.gcxy.service.Impl;

import com.gcxy.entity.AccountInfo;
import com.gcxy.mapper.AccountInfoMapper;
import com.gcxy.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-12
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {
    @Autowired
    private AccountInfoMapper accountInfoMapper;


    @Override
    public List<AccountInfo> getAll() {
        return accountInfoMapper.selectAll();
    }

    @Override
    public AccountInfo getAccount(String account) {
        return accountInfoMapper.getByAccount(account);
    }
    //新增
    @Override
    public int addAccount(AccountInfo accountInfo) {
        return accountInfoMapper.insertAccount(accountInfo);
    }

    //修改
    @Override
    public int updateAccount(AccountInfo accountInfo){
        return accountInfoMapper.updateAccount(accountInfo);
    }

}
