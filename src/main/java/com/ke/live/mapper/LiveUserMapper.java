package com.ke.live.mapper;

import com.ke.live.entity.LiveUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据appId更新openId
     * @param appId 小程序唯一ID
     * @param openId 用户标识
     */
    @Update("update live_user set open_id = #{openId} where app_id = #{appId}")
    void updateOpenIdByAppId(String openId,String appId);

    /**
     * 根据appId查询openId
     * @param appId 小程序唯一ID
     * @return openId 用户标识
     */
    @Select("select open_id from live_user where app_id = #{appId}")
    String selectOpenIdByAppId(String appId);
}
