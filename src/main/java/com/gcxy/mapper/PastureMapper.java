package com.gcxy.mapper;

import com.gcxy.dao.Pasture.AddPastureDao;
import com.gcxy.entity.Pasture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 牧场管理表 Mapper 接口
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Mapper
public interface PastureMapper extends BaseMapper<Pasture> {
    //查询牧场所有信息
    @Select("select * from pasture,data_dic_val where val_id=data_dic_val.id")
    List<Pasture> selectAll();
    //根据牧场名称查数据
    @Select("select * from pasture where val_id=data_dic_val.id and pasture_name=#{pasture_name}")
    Pasture getByPastureName(String pastureName);
    @Insert("insert into pasture values(#{pasture_name},#{field_scale},#{feed_scale},#{province},#{city},#{district}," +
            "#{longitude},#{latitude},#{addr_detail},#{val_id})")
    int insertPasture(AddPastureDao addPastureDao);

}
