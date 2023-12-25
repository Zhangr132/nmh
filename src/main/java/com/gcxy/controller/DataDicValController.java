package com.gcxy.controller;


import com.gcxy.config.R;
import com.gcxy.dao.DataDicVal.AddDataDicValDao;
import com.gcxy.dao.DataDicVal.DeleteDataDicValDao;
import com.gcxy.entity.DataDicVal;
import com.gcxy.entity.MyPage;
import com.gcxy.service.DataDicValService;
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
 * 数据字典值管理
 * <p>
 * 数据字典值表 前端控制器
 * </p>
 * @module 数据字典值管理
 * @author zhangr132
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/DataDicVal")
@Api("数据字典值模块")
public class DataDicValController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    DataDicValService dataDicValService;

    @ApiOperation("查询畜牧信息")
    @PostMapping("/selectDicVal")
    public R selectDicVal(@Valid @RequestBody MyPage<DataDicVal> myPage){
        R result=dataDicValService.selectDicVal(myPage);
        return result;
    }

    @ApiOperation("新增畜牧品种")
    @PostMapping("/addDicVal")
    public R addDicVal(@Valid @RequestBody AddDataDicValDao addDataDicValDao){
        logger.info("正在进入新增");
        boolean result=dataDicValService.addDicVal(addDataDicValDao);
        if (result){
            return R.Success("新增成功");
        }
        return R.Failed("新增失败");
    }

    @ApiOperation("修改状态")
    @PostMapping("/stopDicVal")
    public R stopDicVal(@Valid @RequestBody DeleteDataDicValDao deleteDataDicValDao){
        logger.info("正在进入修改状态");
        boolean row=dataDicValService.stopDicVal(deleteDataDicValDao);
        if (row){
            return R.Success("修改状态成功");
        }
        return R.Failed("修改状态失败");
    }

}
