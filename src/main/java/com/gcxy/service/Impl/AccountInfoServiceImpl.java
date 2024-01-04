package com.gcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gcxy.config.R;
import com.gcxy.dao.AccountInfo.*;
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

import java.util.Date;
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
        boolean result=Md5Util.passwordVerify(loginDao.getPassword(),accountInfoMapper.getByAccName(loginDao.getAccName()).getPassword());
        if(result){
            //修改用户最后登录时间
            AccountInfo accountInfo=AccountInfo.builder()
                    .updateTime(new Date())
                    .account(accountInfoMapper.getByAccName(loginDao.getAccName()).getAccount())
                    .build();
            accountInfoMapper.updateTime(accountInfo);
            //登录生成token
            Map<String,String> map=new HashMap<>();
            map.put("accName",loginDao.getAccName());
            map.put("account",accountInfo.getAccount());
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
    public boolean updateAccount(UpdateDao updateDao) throws Exception {
        AccountInfo accountInfo=accountInfoMapper.getByAccount(updateDao.getAccount());
        if(accountInfo!=null){
            AccountInfo accountInfo1=AccountInfo.builder()
                    .account(updateDao.getAccount())
                    .accName(updateDao.getAccName())
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
    public boolean deleteAccount(DeleteDao deleteDao){
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
     * @param selectPageDao
     * @return
     */
    @Override
    public R selectAccount(SelectPageDao selectPageDao){
        QueryWrapper<AccountInfo> queryWrapper=new QueryWrapper<>();
        //将pageSize和pageNumber放入Page中
        Page<AccountInfo> page=new Page<>(selectPageDao.getPageNumber(),selectPageDao.getPageSize());
        queryWrapper.select("account", "acc_name", "acc_phone", "status", "create_time", "update_time")
                    .like(selectPageDao.getAccName()!=null,"acc_name",selectPageDao.getAccName())
                    .like(selectPageDao.getAccPhone()!=null,"acc_phone",selectPageDao.getAccPhone())
                    .eq(selectPageDao.getStatus()!=null,"status",selectPageDao.getStatus());
        IPage<AccountInfo> accountInfoPage =accountInfoMapper.selectPage(page,queryWrapper);
        List<AccountInfo> records=accountInfoPage.getRecords();
        Map responseData=new HashMap<>();
        responseData.put("data", records);
        responseData.put("total", accountInfoPage.getTotal()); // 总记录数
        responseData.put("size", accountInfoPage.getSize()); // 每页显示数量
        responseData.put("current", accountInfoPage.getCurrent()); // 当前页码
//        responseData.put("orders", accountInfoPage.orders()); // 排序信息
//        responseData.put("optimizeCountSql", accountInfoPage.optimizeCountSql()); // 是否优化count语句
        responseData.put("pages", accountInfoPage.getPages()); // 总页数
        return R.Success(accountInfoMapper.selectPage(page,queryWrapper) );
    }

    @Override
    public List<AccountInfo> getAll() {
        return accountInfoMapper.selectAll();
    }

}