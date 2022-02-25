package com.ke.live.mapper;

import com.ke.live.entity.LiveMiniCourseLabel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 小凡
 */
@Mapper
public interface LiveMiniCourseLabelMapper {
    /**
     * 根据直播间Id查询直播标签
     *
     * @param roomId 直播间ID
     * @return List<LiveRelationLabel>
     */
    @Select("select * from live_mini_course_label where room_id =:roomId order by live_label")
    List<LiveMiniCourseLabel> findLiveLabelByRoomId(Integer roomId);

    /**
     * 根据直播间ID删除该直播
     *
     * @param roomId 直播间ID
     */
    @Delete("delete LiveMiniCourseLabel where room_id =:roomId")
    void deleteLiveLabelByRoomId(Integer roomId);
    /**
     * 插入一条新数据
     * @param liveMiniCourseLabel 直播标签
     */
    void save(LiveMiniCourseLabel liveMiniCourseLabel);
}
