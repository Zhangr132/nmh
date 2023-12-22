package com.gcxy.mapper;

import com.gcxy.entity.Pasture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from pasture where pasture_Id=#{pastureId}")
    Pasture getByPastureId(Integer pastureId);
    //根据牧场名称查数据
    @Select("select * from pasture where pasture_name=#{pasture_name}")
    Pasture getByPastureName(String pastureName);
    //新增牧场
    @Insert("insert into pasture (pasture_name, field_scale, feed_scale, province, city, district, longitude, latitude, addr_detail, val_id) " +
            "values(#{pastureName},#{fieldScale},#{feedScale},#{province},#{city},#{district},#{longitude},#{latitude},#{addrDetail},#{valId})")
    int insertPasture(Pasture pasture);

    //修改牧场
    @Update("update pasture set pasture_name=#{pastureName},field_scale=#{fieldScale},feed_scale=#{feedScale},province=#{province},city=#{city}," +
            "district=#{district},longitude=#{longitude},latitude=#{latitude},addr_detail=#{addrDetail},val_id=#{valId} where pasture_Id=#{pastureId}")
    int updatePasture(Pasture pasture);
    //修改牧场状态
    @Update("update pasture set status=#{status} where pasture_Id=#{pastureId}")
    int deletePasture(Pasture pasture);
}
