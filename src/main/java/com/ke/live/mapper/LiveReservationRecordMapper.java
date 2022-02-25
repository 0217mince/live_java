package com.ke.live.mapper;

import com.ke.live.entity.LiveReservationRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 小凡
 */
@Mapper
public interface LiveReservationRecordMapper {
    /**
     * 根据直播间Id和用户Id获得预约记录
     *
     * @param roomId 直播间ID
     * @param userId 用户ID、
     * @return LiveReservationRecord
     */
    @Select("select * from live_reservation_record where room_id =#{roomId} and user_id =#{userId}")
    LiveReservationRecord getLiveReservationRecordByRoomIdAndUserId(Integer roomId, String userId);

    /**
     * 根据直播信息查询预约记录
     * @param roomId 房间号
     * @return LiveReservationRecord
     */
    @Select("select * from live_reservation_record where room_id = #{roomId} and reservation_status = 1 and message_reminder_status = 0")
    List<LiveReservationRecord> findByRoomIdAndReservationStatus(Integer roomId);

    /**
     * 根据房间号更新预约状态
     * @param roomId 房间号
     */
    @Update("update live_reservation_record set message_reminder_status = 1 where room_id = #{roomId} and reservation_status = 1")
    void updateMessageReminderStatusByRoomIds(Integer roomId);

    /**
     * 根据Id获取预约记录
     * @param recordId 预约记录表Id
     * @return LiveReservationRecord
     */
    @Select("select * from live_reservation_record where record_id =#{recordId}")
    LiveReservationRecord getLiveReservationRecordByRecordId(String recordId);

    /**
     * 插入一条记录
     * @param liveReservationRecord 预约信息
     */
    @Insert("insert into live_reservation_record " +
            "value(#{recordId},#{liveMiniCourseId},#{roomId},#{userId},#{deviceId},#{startTime},#{messageReminderStatus},#{reservationStatus},#{modifiedTime})")
    void save(LiveReservationRecord liveReservationRecord);
}
