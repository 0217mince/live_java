package com.ke.live.mapper;

import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.entity.LiveMiniCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小凡
 * @date 2022/1/29
 */
@Mapper
@Repository
public interface LiveMiniCourseMapper {
    /**
     * 查询所有
     *
     * @return 所有直播信息
     */
    @Select("select * FROM live_mini_course")
    ArrayList<LiveMiniCourse> findAll();

    /**
     * 根据搜索条件分页查找直播
     *
     * @param liveMiniCourseSearchDTO 搜索条件
     * @return List<LiveMiniCourse>直播信息
     */
    @Select("<script> " +
            "select * from live_mini_course " +
            "<where>" +
            "<if test='roomId != null || roomId == 0'>" +
            " and room_id=#{roomId}" +
            "</if>" +
            "<if test='startTime != null'>" +
            " and <![CDATA[start_time >= #{startTime} and start_time <= #{endTime}]]>" +
            "</if>" +
            "<if test='anchorName != null'>" +
            " and anchor_name like concat('%',#{anchorName,jdbcType=VARCHAR},'%')" +
            "</if>" +
            "<if test='liveStudioTitle != null'>" +
            " and live_studio_title like concat('%',#{liveStudioTitle,jdbcType=VARCHAR},'%')" +
            "</if>" +
            "<if test='page != null'>" +
            " and limit (#{page},#{size})" +
            "</if>" +
            "</where>" +
            "</script>")
    ArrayList<LiveMiniCourse> findLiveMiniCourseByOperateDTO(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO);

    /**
     * 根据搜索条件分页查找直播个数
     *
     * @param liveMiniCourseSearchDTO 搜索条件
     * @return 直播条数
     */
    @Select("<script> " +
            "select count(*) from live_mini_course " +
            "<where>" +
            "<if test='roomId != null || roomId == 0'>" +
            " and room_id=#{roomId}" +
            "</if>" +
            "<if test='startTime != null'>" +
            " and <![CDATA[start_time >= #{startTime} and start_time <= #{endTime}]]>" +
            "</if>" +
            "<if test='anchorName != null'>" +
            " and anchor_name like concat('%',#{anchorName,jdbcType=VARCHAR},'%')" +
            "</if>" +
            "<if test='liveStudioTitle != null'>" +
            " and live_studio_title like concat('%',#{liveStudioTitle,jdbcType=VARCHAR},'%')" +
            "</if>" +
            "</where>" +
            "</script>")
    Integer getLiveMiniCourseCountByOperateDTO(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO);

    /**
     * 根据roomId查找对应的直播信息
     *
     * @param roomId 直播间Id
     * @return List<LiveSource>
     */
    @Select("select * from live_mini_course where room_id=#{roomId}")
    LiveMiniCourse getLiveMiniCourseByRoomId(Integer roomId);

    /**
     * 根据直播间Id更新审核状态
     *
     * @param roomId 直播间ID
     * @param reviewStatus 审核状态
     */
    @Update("update live_mini_course set review_status=#{reviewStatus where room_id=#{roomId}}")
    void updateReviewStatusByRoomId(Integer roomId,Integer reviewStatus);

    /**
     * 返回一个审核已通过的直播
     *
     * @return LiveSource
     */
    @Select("select * from live_mini_course where review_status = 1 and live_studio_status in (101,102) order by liveStudioStatus limit 1")
    List<LiveMiniCourse> findOneLiveSource();

    /**
     * 根据标签查找对应的直播
     * @param boxLink 直播标签
     * @param startIndex 查询的初始位置
     * @param pageSize 分页大小
     *
     * @return List<LiveMiniCourse>
     */
    @Select("select a from live_mini_course a,live_mini_course_label b where b.box_link =#{boxLink} and a.room_id=b.room_id and a.live_studio_status in (101,102,103) and a.review_status = 1 " +
            "order by a.live_studio_status asc,a.start_time desc limit #{startIndex},#{pageSize}")
    List<LiveMiniCourse> findLiveMiniCourseByLiveLabel(String boxLink,int startIndex,int pageSize);

    /**
     * 根绝审核状态查询所有符合条件的直播
     * @param reviewStatus 审核状态
     * @return List<LiveMiniCourse>
     */
    @Select("select * from live_mini_course where review_status =#{reviewStatus} and live_studio_status in (101,102,103) order by live_studio_status asc,start_time desc")
    List<LiveMiniCourse> findAllLiveMiniCourseByReviewStatus(Integer reviewStatus);

    /**
     * 查找直播状态为102 且已经开始的直播
     * @return List<LiveMiniCourse>
     */
    @Select("select * from live_mini_course where start_time <= NOW() and live_studio_status = 102")
    List<LiveMiniCourse> findPendingLiveMiniCourse();

    /**
     * 根据房间号更新直播状态
     * @param liveStudioStatus 直播间状态
     * @param roomId 房间号
     */
    @Select("update live_mini_course set live_studio_status =#{liveStudioStatus} where room_id = :roomId")
    void updateLiveStudioStatusByRoomIdI(Integer liveStudioStatus,Integer roomId);

    /**
     * 新增一个直播
     * @param liveMiniCourse 直播信息
     */
    @Insert("insert into live_mini_course(live_mini_course_id,room_id,live_studio_title,live_studio_status,source,anchor_name,start_time,end_time,review_status,live_cover,modified_time)" +
            "values(#{liveMiniCourseId},#{roomId},#{liveStudioTitle},#{liveStudioStatus},#{source},#{anchorName},#{startTime},#{endTime},#{reviewStatus},#{liveCover},#{modifiedTime}) ")
    void insertOne(LiveMiniCourse liveMiniCourse);

    /**
     * 更新一条记录信息
     * @param liveMiniCourse 直播信息
     */
    @Update("update live_mini_course(live_studio_title,live_studio_status,anchor_name,start_time,end_time,live_cover,modified_time)" +
            "value(#{liveStudioTitle},#{liveStudioStatus},#{anchorName},#{startTime},#{endTime},#{liveCover},#{modifiedTime})" +
            "where 'live_mini_course_id' = #{liveMiniCourseId}")
    void update(LiveMiniCourse liveMiniCourse);
}
