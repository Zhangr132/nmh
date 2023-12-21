package com.gcxy.mapper;

import com.gcxy.entity.AccountInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhangr132
 * @since 2023-12-12
 */
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {
    //获取所有用户数据
    @Select("select * from account_info")
    List<AccountInfo> selectAll();
    @Select("select * from account_info where account = #{account}")
    AccountInfo getByAccount(String account);
    //新增
    @Insert("insert into account_info values (#{account},#{accName},#{password},#{accPhone},#{status},#{createTime},#{updateTime})")
    int insertAccount(AccountInfo accountInfo);
    //修改
    @Update("update account_info set account=#{account},acc_name=#{accName},password=#{password},acc_phone=#{accPhone},status=#{status} where account=#{account}")
    int updateAccount(AccountInfo accountInfo);
    //修改用户状态
    @Update("update account_info set status=#{status} where account=#{account}")
    int updateStatus(AccountInfo accountInfo);

}