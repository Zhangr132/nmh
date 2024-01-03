package com.gcxy.mapper;

import com.gcxy.entity.DataDic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Mapper
public interface DataDicMapper extends BaseMapper<DataDic> {
    //根据Id查
    @Select("select * from data_dic where Id=#{Id}")
    DataDic getDataDicById(Integer Id);

    @Insert("insert into data_dic (dic_name) values (#{dicName})")
    int insertDataDic(DataDic dataDic);

}
