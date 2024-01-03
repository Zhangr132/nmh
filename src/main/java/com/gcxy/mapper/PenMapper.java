package com.gcxy.mapper;

import com.gcxy.entity.Pen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 圈舍管理表 Mapper 接口
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-21
 */
@Mapper
public interface PenMapper extends BaseMapper<Pen> {
    //根据pen_id查询
    @Select("select * from pen where pen_Id=#{penId}")
    Pen getByPenId(Integer penId);

    //根据Pen查询(多个数据记得加上@Param注解）
    @Select("select * from pen where ranch_id=#{ranchId} and pen_name=#{penName}")
    Pen getByDao(@Param("ranchId") Integer ranchId,@Param("penName") String penName);

    //添加圈舍
    @Insert("insert into pen (ranch_id, pen_name) " +
            "values (#{ranchId},#{penName})")
    int insertPen(Pen pen);

    //修改圈舍
    @Update("update pen set pen_name=#{penName} where pen_Id=#{penId}")
    int updatePen(Pen pen);

    //停(启)用圈舍
    @Update("update pen set status=#{status} where pen_Id=#{penId}")
    int deletePen(Pen pen);

}
