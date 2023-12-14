package com.gcxy.service.Impl;

import com.gcxy.dao.LoginDao;
import com.gcxy.dao.RegisterDao;
import com.gcxy.entity.AccountInfo;
import com.gcxy.mapper.AccountInfoMapper;
import com.gcxy.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcxy.utils.JwtTokenUtil;
import com.gcxy.utils.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 登录
     * @param loginDao
     * @return
     * @throws Exception
     */
    @Override
    public String login(LoginDao loginDao) throws Exception {
        boolean result=Md5Util.passwordVerify(loginDao.getPassword(),accountInfoMapper.getByAccount(loginDao.getAccount()).getPassword());
        if(result){
            Map<String,String> map=new HashMap<>();
            map.put("accName",accountInfoMapper.getByAccount(loginDao.getAccount()).getAccName());
            map.put("account",loginDao.getAccount());
            String token= JwtTokenUtil.createToken(map);
            logger.info("用户登录成功,生成的Token为："+token);
            return token;
        }
        return null;
    }

    /**
     * 注册
     * @param registerDao
     * @return
     * @throws Exception
     */
    @Override
    public boolean register(RegisterDao registerDao) throws Exception {
        AccountInfo accountInfo=accountInfoMapper.getByAccount(registerDao.getAccount());
        if (accountInfo==null){
            String ps= Md5Util.md5(registerDao.getPassword());
            AccountInfo accountInfo1=AccountInfo.builder()
                    .account(registerDao.getAccount())
                    .accName(registerDao.getAccName())
                    .password(ps)
                    .accPhone(registerDao.getAccPhone())
                    .isEnable(true)
                    .build();
            int count=accountInfoMapper.insertAccount(accountInfo1);
            return count>0;

        }
        return false;
    }





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
