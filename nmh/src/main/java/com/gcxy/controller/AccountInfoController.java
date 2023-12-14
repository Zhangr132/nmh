package com.gcxy.controller;


import com.gcxy.entity.AccountInfo;
import com.gcxy.service.AccountInfoService;
import com.gcxy.utils.JwtTokenUtil;
import com.gcxy.utils.Md5Util;
import com.gcxy.config.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-12
 */
@RestController
@RequestMapping("/accountInfo")
@Api("用户管理模块")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    @ApiOperation("获取用户数据接口")
    @GetMapping ("/getAll")
    public R getAll(){

//        Claims claims=JwtTokenUtil.verify();
        return R.Success(accountInfoService.getAll());
    }


    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    public R login(@ApiParam  @RequestBody AccountInfo requestBody,@ApiParam HttpServletRequest req,@ApiParam HttpServletResponse response){
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
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public R register(@ApiParam String account,@ApiParam String accName,@ApiParam  String password,@ApiParam String accPhone){
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
                accountInfo1.setIsEnable(true);
                int result = accountInfoService.addAccount(accountInfo1);
                System.out.println(result);
                return R.Success("注册成功！");
            } catch (Exception e){
                e.printStackTrace();
                return R.Failed("注册失败！");
            }
        }
        return R.Failed("注册失败！");
    }

    @ApiOperation("修改人员信息接口")
    @PutMapping("/updateUser")
    public R find(@ApiParam String account,@ApiParam String accName,@ApiParam String password,@ApiParam String accPhone,@ApiParam Boolean isEnable){
        AccountInfo accountInfo=accountInfoService.getAccount(account);
        System.out.println(accountInfo);
        if (accountInfo != null) {
            try {
                String ps = Md5Util.md5(password);
                // 更新用户信息
                accountInfo.setAccName(accName);
                accountInfo.setPassword(ps);
                accountInfo.setAccPhone(accPhone);
                accountInfo.setIsEnable(isEnable);
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
    }

}
