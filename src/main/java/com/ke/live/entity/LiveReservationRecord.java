package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kzf
 * @date 2021/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "live_reservation_record")
public class LiveReservationRecord {
    private Integer id;
    @TableField("record_id")
    private String recordId;
    @TableField("live_mini_course_id")
    private String liveMiniCourseId;
    @TableField("room_id")
    private Integer roomId;
    @TableField("user_id")
    private String userId;
    @TableField("device_id")
    private Integer deviceId;
    @TableField("start_Time")
    private Date startTime;
    @TableField("message_reminder_status")
    private Integer messageReminderStatus;
    @TableField("reservation_status")
    private Integer reservationStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("modified_time")
    private Date modifiedTime;

}
