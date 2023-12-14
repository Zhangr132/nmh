package com.gcxy.utils;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/gcxy_nmh?serverTimezone=UTC";
        String username="root";
        String password="123456";
        String author="zhangr132";
        String module="";//表示项目的模块名称
        String outPath=System.getProperty("user.dir")+"/"+module+"/src/main/java";//输出目录
        String parent="com.gcxy";//父包的名称
        String moduleName="";//父包模块名
        String entity="entity";//Entity 实体类包名
        String mapper="mapper";//Mapper 包名
        String service="service";//Service 包名
        String serviceImpl="service.Impl";//Service Impl 包名
        String controller="controller";//Controller 包名*/
        String mapperXml="mapper.xml";//Mapper XML 包名


        List<String> tables=new ArrayList<>();
        tables.add("account_info");

        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPath) // 指定输出目录
                            .disableOpenDir() //生成之后不打开目录
                            .dateType(DateType.ONLY_DATE) //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .commentDate("yyyy-MM-dd");//注释日期
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity(entity)
                            .mapper(mapper)
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller)
                            .xml(mapperXml);
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                //模板配置
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .entityBuilder()    //开启生成实体类
                            .enableLombok()     //开启lombok模型
                            .mapperBuilder()    //开启生成mapper
                            .superClass(BaseMapper.class)//设置父类
                            .enableMapperAnnotation()   //开启 @Mapper 注解
                            .formatMapperFileName("%sMapper")//格式化mapper名称
                            .formatXmlFileName("%sMapper")//格式化 xml 实现类文件名称
                            //开启生成service及impl
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 实现类文件名称
                            //开启生成controller
                            .controllerBuilder()
                            // 映射路径使用连字符格式，而不是驼峰
                            //.enableHyphenStyle()
                            .formatFileName("%sController")//格式化文件名称
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
