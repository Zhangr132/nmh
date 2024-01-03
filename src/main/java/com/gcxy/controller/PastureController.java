package com.gcxy.controller;

import com.gcxy.config.R;
import com.gcxy.dao.Pasture.AddPastureDao;
import com.gcxy.dao.Pasture.DeletePastureDao;
import com.gcxy.dao.Pasture.UpdatePastureDao;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pasture;
import com.gcxy.service.PastureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 牧场管理
 * <p>
 * 牧场管理表 前端控制器
 * </p>
 *
 * @module 用户管理
 * @author zhangr132
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/pasture")
@Api("牧场管理模块")
public class PastureController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PastureService pastureService;

    @ApiOperation("查询牧场信息")
    @PostMapping("/selectPasture")
    public R selectPasture(@Valid @RequestBody MyPage<Pasture> myPage){
        R result=pastureService.selectPasture(myPage);
        return result;
    }

    @ApiOperation("新增牧场")
    @PostMapping("/addPasture")
    public R addPasture(@Valid @RequestBody AddPastureDao addPastureDao){
        logger.info("正在进入新增牧场");
        boolean result=pastureService.addPasture(addPastureDao);
        if(result){
            return R.Success("新增成功");
        }
        return R.Failed("新增失败");
    }

    @ApiOperation("修改牧场")
    @PostMapping("/updatePasture")
    public R updatePasture(@Valid @RequestBody UpdatePastureDao updatePastureDao){
        logger.info("正在进入修改牧场");
        boolean row=pastureService.updatePasture(updatePastureDao);
        if (row){
            return R.Success("修改成功");
        }
        return R.Failed("修改失败，用户不存在");
    }

    @ApiOperation("停(启)用牧场")
    @PostMapping("/deletePasture")
    public R deletePasture(@Valid @RequestBody DeletePastureDao deletePastureDao){
        logger.info("正在进入停(启)用牧场");
        boolean row=pastureService.deletePasture(deletePastureDao);
        if (row){
            return R.Success("修改牧场状态成功");
        }
        return R.Failed("修改牧场状态失败");
    }
}
