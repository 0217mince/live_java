package com.ke.live.mapper;

import com.ke.live.entity.LiveUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 小凡
 */
@Mapper
public interface LiveUserMapper {
    /**
     * 新增一条数据
     * @param liveUser 用户信息
     */
    @Insert("insert into live_user(user_id,open_id,session_key,app_id,pass_word) " +
            "value(#{userId},#{openId},#{sessionKey},#{appId},#{passWord})")
    void save(LiveUser liveUser);

    /**
     * 根据用户id查找用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    @Select("select * from live_user where user_id = #{userId}")
    LiveUser selectByUserId(String userId);
}
