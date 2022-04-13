package com.ke.live.service;

import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveReservationRecord;
import com.ke.live.mapper.LiveMiniCourseLabelMapper;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.mapper.LiveReservationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author 小凡
 */
@Service
public class LiveMiniCourseService {
    @Autowired
    private LiveMiniCourseMapper liveMiniCourseMapper;
    @Autowired
    private LiveReservationRecordMapper liveReservationRecordMapper;

    /**
     * 预约微信小程序直播
     * @param roomId 直播间ID
     * @param userId 微信小程序用户身份主键
     */
    public void bookingLiveMiniCourse(Integer roomId,String userId){
        LiveMiniCourse liveMiniCourse = liveMiniCourseMapper.getLiveMiniCourseByRoomId(roomId);
        LiveReservationRecord liveReservationRecord = new LiveReservationRecord();
        liveReservationRecord.setRoomId(roomId);
        liveReservationRecord.setDeviceId(0);
        liveReservationRecord.setStartTime(liveMiniCourse.getStartTime());
        liveReservationRecord.setLiveMiniCourseId(liveMiniCourse.getLiveMiniCourseId());
        //生成UUID当作唯一的表ID
        liveReservationRecord.setRecordId(UUID.randomUUID().toString().replaceAll("-",""));
        liveReservationRecord.setUserId(userId);
        liveReservationRecord.setModifiedTime(new Date());
        //设置预约状态为已预约,1为已预约，0为未预约
        liveReservationRecord.setReservationStatus(1);
        //设置消息推送为未提醒,1为已提醒，0为未提醒
        liveReservationRecord.setMessageReminderStatus(0);
        liveReservationRecordMapper.save(liveReservationRecord);
    }

}
