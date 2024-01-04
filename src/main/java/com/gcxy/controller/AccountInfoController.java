package com.gcxy.controller;


import com.gcxy.dao.AccountInfo.*;
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

    /**
     * 测试用接口
     * @return
     */
    @ApiOperation("获取所有用户数据")
    @GetMapping ("/getAll")
    public R getAll(){

//        Claims claims=JwtTokenUtil.verify();
        return R.Success(accountInfoService.getAll());
    }


    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public R login(@Valid @RequestBody LoginDao loginDao) throws Exception {
        logger.info("正在进行登录");
        String result=accountInfoService.login(loginDao);
        if(result!=null){
            return R.Success("登录成功！",result);
        }
        System.out.println("登陆失败！");
        return R.Failed("登录失败！");

    }

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
    public R selectAccount(@Valid @RequestBody SelectPageDao selectPageDao){
        logger.info("正在进入分页查询");
        R result =accountInfoService.selectAccount(selectPageDao);
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