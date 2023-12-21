package com.gcxy.controller;


import com.gcxy.dao.AccountInfo.DeleteDao;
import com.gcxy.dao.AccountInfo.LoginDao;
import com.gcxy.dao.AccountInfo.RegisterDao;
import com.gcxy.dao.AccountInfo.UpdateDao;
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

    @ApiOperation("分页查询")
    @PostMapping("/selectAccount")
    public R selectAccount(@Valid @RequestBody MyPage<AccountInfo> myPage){
        R result =accountInfoService.selectAccount(myPage);
        return result;
    }

    @ApiOperation("修改用户")
    @PostMapping("/updateAccount")
    public R updateAccount(@Valid @RequestBody UpdateDao updateDao) throws Exception {
        logger.info("正在进行修改");
        boolean row =accountInfoService.updateAccount(updateDao);
        if(row){
            return R.Success("修改成功");
        }
        return R.Failed("修改失败，用户不存在");
    }

    @ApiOperation("停（启）用用户")
    @PostMapping("/deleteAccount")
    public R deleteAccount(@Valid @RequestBody DeleteDao deleteDao){
        logger.info("正在进行启（停）用用户");
        boolean row =accountInfoService.deleteAccount(deleteDao);
        if(row){
            return R.Success("用户状态修改成功");
        }
        return R.Failed("用户状态修改失败，用户不存在");
    }

}