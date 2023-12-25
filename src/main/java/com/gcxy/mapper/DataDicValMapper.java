package com.gcxy.mapper;

import com.gcxy.entity.DataDicVal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 数据字典值表 Mapper 接口
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Mapper
public interface DataDicValMapper extends BaseMapper<DataDicVal> {
    //根据Id查询
    @Select("select * from data_dic_val where Id=#{Id}")
    DataDicVal getById(Integer Id);

    //新增
    @Insert("insert into data_dic_val (dic_id,val_value) values (#{dicId},#{valValue})")
    int insertDataDicVal(DataDicVal dataDicVal);

    //停(启)用
    @Update("update data_dic_val set status=#{status} where Id=#{id}")
    int deleteDataDicVal(DataDicVal dataDicVal);

}
