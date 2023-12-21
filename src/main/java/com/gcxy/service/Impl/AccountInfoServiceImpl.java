package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.DeleteDao;
import com.gcxy.dao.LoginDao;
import com.gcxy.dao.RegisterDao;
import com.gcxy.dao.UpdateDao;
import com.gcxy.entity.AccountInfo;
import com.gcxy.entity.MyPage;
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
//        System.out.println("测试点");
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
        String Yz="YZ";//前缀
        int i = 1; // 初始序号
        while (i <= 9999) {
            String newAccount = Yz + String.format("%04d", i); // 生成新账号
            AccountInfo existingAccount = accountInfoMapper.getByAccount(newAccount);
            if (existingAccount == null) {
                    String ps= Md5Util.md5(registerDao.getAccPhone().substring(registerDao.getAccPhone().length() - 6));
                    AccountInfo accountInfo=AccountInfo.builder()
                            .account(newAccount)
                            .accName(registerDao.getAccName())
                            .password(ps)
                            .accPhone(registerDao.getAccPhone())
                            .status(true)
                            .build();
                    int count=accountInfoMapper.insertAccount(accountInfo);
                    return count>0;
            }
            i++; // 序号加1
        }

        return false;
    }

    /**
     * 修改用户信息
     * @param updateDao
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(UpdateDao updateDao) throws Exception {
        AccountInfo accountInfo=accountInfoMapper.getByAccount(updateDao.getAccount());
        if(accountInfo!=null){
            String ps=Md5Util.md5(updateDao.getPassword());
            AccountInfo accountInfo1=AccountInfo.builder()
                    .account(updateDao.getAccount())
                    .accName(updateDao.getAccName())
                    .password(ps)
                    .accPhone(updateDao.getAccPhone())
                    .status(updateDao.getStatus())
                    .build();
            int count=accountInfoMapper.updateAccount(accountInfo1);
            return count>0;
        }
        return false;
    }

    /**
     * 启（停)用用户
     * @param deleteDao
     * @return
     */
    public boolean delete(DeleteDao deleteDao){
        AccountInfo accountInfo=accountInfoMapper.getByAccount(deleteDao.getAccount());
        if(accountInfo!=null){
            AccountInfo accountInfo1= AccountInfo.builder()
                    .account(deleteDao.getAccount())
                    .status(deleteDao.getStatus())
                    .build();
            int count=accountInfoMapper.updateStatus(accountInfo1);
            return count>0;
        }
        return false;
    }

    /**
     * 分页查询
     * @param myPage
     * @return
     */
    @Override
    public R pageAccount(MyPage<AccountInfo> myPage){
        QueryWrapper<AccountInfo> queryWrapper=new QueryWrapper<>();
        Page<AccountInfo> page=new Page<>(myPage.getPageNumber(),myPage.getPageSize());
        if(myPage.getData()!=null){
            queryWrapper.like(myPage.getData().getAccName()!=null,"acc_name",myPage.getData().getAccName())
                    .like(myPage.getData().getAccPhone()!=null,"acc_phone",myPage.getData().getAccPhone())
                    .eq(myPage.getData().getStatus()!=null,"is_enable",myPage.getData().getStatus());
        }
        accountInfoMapper.selectPage(page,queryWrapper);
        List<AccountInfo> records=page.getRecords();
        Map data=new HashMap<>();
        data.put("data",records);
        return R.Success(data);
    }

    @Override
    public List<AccountInfo> getAll() {
        return accountInfoMapper.selectAll();
    }

}