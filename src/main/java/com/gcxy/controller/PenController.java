package com.gcxy.controller;


import com.gcxy.config.R;
import com.gcxy.dao.Pen.AddPenDao;
import com.gcxy.dao.Pen.DeletePenDao;
import com.gcxy.dao.Pen.UpdatePenDao;
import com.gcxy.entity.MyPage;
import com.gcxy.entity.Pen;
import com.gcxy.service.PenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 圈舍管理
 * <p>
 * 圈舍管理表 前端控制器
 * </p>
 * @module 圈舍管理
 * @author zhangr132
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/pen")
@Api("圈舍管理模块")
public class PenController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PenService penService;

    @ApiOperation("查询圈舍")
    @PostMapping("/selectPagePen")
    public R selectPagePen(@Valid @RequestBody MyPage<Pen> myPage){
        logger.info("正在进入圈舍的分页查询");
        R result=penService.selectPagePen(myPage);
        return result;
    }

    @ApiOperation("新增圈舍")
    @PostMapping("/addPen")
    public R addPen(@Valid @RequestBody AddPenDao addPenDao){
        logger.info("正在进入新增圈舍");
        boolean result=penService.addPen(addPenDao);
        if (result){
            return R.Success("新增成功");
        }
        return R.Failed("新增失败");

    }

    @ApiOperation("修改圈舍")
    @PostMapping("/updatePen")
    public R updatePen(@Valid @RequestBody UpdatePenDao updatePenDao){
        logger.info("正在进入修改圈舍");
        boolean row=penService.updatePen(updatePenDao);
        if (row){
            return R.Success("修改成功");
        }
        return R.Failed("修改失败");
    }

    @ApiOperation("停(启)用圈舍")
    @PostMapping("/deletePen")
    public R deletePen(@Valid @RequestBody DeletePenDao deletePenDao){
        logger.info("正在进入停(启)用圈舍");
        boolean row=penService.deletePen(deletePenDao);
        if (row){
            return R.Success("修改状态成功");
        }
        return R.Failed("修改状态失败");
    }
}
