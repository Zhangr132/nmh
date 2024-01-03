package com.gcxy.controller;


import com.gcxy.config.R;
import com.gcxy.dao.DataDic.AddDataDicDao;
import com.gcxy.service.DataDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/data-dic")
@Api("数据字典模块")
public class DataDicController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private DataDicService dataDicService;

    @ApiOperation("新增菜单栏")
    @PostMapping("/addDataDic")
    public R addDataDic(@Valid @RequestBody AddDataDicDao addDataDicDao){
        logger.info("正在进入新增");
        boolean result=dataDicService.addDataDic(addDataDicDao);
        if (result){
            return R.Success("新增成功");
        }
        return R.Failed("新增失败");
    }


}
