package com.gcxy.controller;


import com.gcxy.dao.DeleteDao;
import com.gcxy.dao.LoginDao;
import com.gcxy.dao.RegisterDao;
import com.gcxy.dao.UpdateDao;
import com.gcxy.entity.AccountInfo;
import com.gcxy.entity.MyPage;
import com.gcxy.service.AccountInfoService;
import com.gcxy.config.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户管理
 * 用户表 前端控制器
 * @module 用户管理
 * @author zhangr132
 * @since 2023-12-12
 */
@RestController
@RequestMapping("/acc")
@Api("用户管理模块")
public class AccountInfoController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountInfoService accountInfoService;

    @ApiOperation("获取所有用户数据")
    @GetMapping ("/getAll")
    public R getAll(){

//        Claims claims=JwtTokenUtil.verify();
        return R.Success(accountInfoService.getAll());
    }


    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public R login(@Valid @RequestBody LoginDao loginDao, HttpServletResponse response) throws Exception {
        logger.info("正在进行登录");
        String result=accountInfoService.login(loginDao);
        if(result!=null){/*
            // 生成token
            String token = JwtTokenUtil.buildJwt(loginDao.getAccount(), loginDao.getPassword());
            System.out.println(token);
            // 将token写入响应头中
            response.addHeader("Authorization", "Bearer " + token);
            response.setContentType("application/json;charset=utf-8");
            System.out.println("登陆成功！");*/
            return R.Success("登录成功！",result);
        }
        System.out.println("登陆失败！");
        return R.Failed("登录失败！");

    }
   /* public R login(@ApiParam  @RequestBody AccountInfo requestBody,@ApiParam HttpServletRequest req,@ApiParam HttpServletResponse response){
        AccountInfo accountInfo = accountInfoService.getAccount(requestBody.getAccount());
        if (accountInfo != null){
            try {
                boolean checkPassword = Md5Util.passwordVerify(requestBody.getPassword(),accountInfo.getPassword());
                if (checkPassword){
                    String token = JwtTokenUtil.buildJwt(accountInfo.getAccName(), accountInfo.getAccount());
//                    // 将token写入响应头中
//                    response.addHeader("Authorization", "Bearer " + token);
//                    response.setContentType("application/json;charset=utf-8");
                    if (token != null) {
                        Cookie cookie = new Cookie("TOKEN", token);
                        cookie.setMaxAge(3600);//设置token有效时间
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                    return R.Success("token值：" + token);
                }
                else {
                    return R.Failed("用户名或者密码错误");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return R.Failed("登录失败！");
    }*/

    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@Valid @RequestBody RegisterDao registerDao) throws Exception {
        logger.info("进入注册！");
        boolean result=accountInfoService.register(registerDao);
        if(result){
            return R.Success("注册成功");
        }
        return R.Failed("注册失败");
    }
    /*public R register(@ApiParam String account,@ApiParam String accName,@ApiParam  String password,@ApiParam String accPhone){
        AccountInfo accountInfo = accountInfoService.getAccount(accName);
        System.out.println(accountInfo);
        if (accountInfo == null){
            try {
                String ps = Md5Util.md5(password);
                AccountInfo accountInfo1 = new AccountInfo();
                accountInfo1.setAccount(account);
                accountInfo1.setAccName(accName);
                accountInfo1.setPassword(ps);
                accountInfo1.setAccPhone(accPhone);
                Date currentTime = new Date();
                accountInfo1.setCreateTime(currentTime);
                accountInfo1.setUpdateTime(currentTime);
                accountInfo1.setStatus(true);
                int result = accountInfoService.addAccount(accountInfo1);
                System.out.println(result);
                return R.Success("注册成功！");
            } catch (Exception e){
                e.printStackTrace();
                return R.Failed("注册失败！");
            }
        }
        return R.Failed("注册失败！");
    }*/

    /*@ApiOperation("获取当前用户信息")
    @GetMapping("/search")
    public R search(@Valid @RequestBody )*/

    @ApiOperation("分页查询")
    @PostMapping("/selectAccount")
    public R pageAccount(@Valid @RequestBody MyPage<AccountInfo> myPage){
        R result =accountInfoService.pageAccount(myPage);
        return result;
    }
    /*public R pageAccount(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                         @RequestParam(value = "accName", required = false) String accName,
                         @RequestParam(value = "accPhone", required = false) String accPhone,
                         @RequestParam(value = "status", required = false) Boolean status){
        R result=accountInfoService.pageAccount(pageNumber,pageSize,accName,accPhone,status);
    }*/

    @ApiOperation("修改用户")
    @PostMapping("/updateAccount")
    public R update(@Valid @RequestBody UpdateDao updateDao) throws Exception {
        logger.info("正在进行修改");
        boolean row =accountInfoService.update(updateDao);
        if(row){
            return R.Success("修改成功");
        }
        return R.Failed("修改失败，用户不存在");
    }
   /* public R update(@ApiParam String account,@ApiParam String accName,@ApiParam String password,@ApiParam String accPhone,@ApiParam Boolean status){
        AccountInfo accountInfo=accountInfoService.getAccount(account);
        System.out.println(accountInfo);
        if (accountInfo != null) {
            try {
                String ps = Md5Util.md5(password);
                // 更新用户信息
                accountInfo.setAccName(accName);
                accountInfo.setPassword(ps);
                accountInfo.setAccPhone(accPhone);
                accountInfo.setStatus(status);
                // 保存更新后的用户信息
                int row = accountInfoService.updateAccount(accountInfo);
                if (row>0) {
                    return R.Success("用户信息修改成功！");
                } else {
                    return R.Failed("用户信息修改失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return R.Failed("用户信息修改失败！");
            }
        }

        return R.Failed("用户不存在！");
    }*/

    @ApiOperation("停（启）用用户")
    @PostMapping("/deleteAccount")
    public R delete(@Valid @RequestBody DeleteDao deleteDao){
        logger.info("正在进行启（停）用用户");
        boolean row =accountInfoService.delete(deleteDao);
        if(row){
            return R.Success("用户状态修改成功");
        }
        return R.Failed("用户状态修改失败，用户不存在");
    }

}