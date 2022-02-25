package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "live_mini_course")
public class LiveMiniCourse {
    @TableId
    private Integer id;
    @TableField("live_mini_course_id")
    private String liveMiniCourseId;
    @TableField("room_id")
    private Integer roomId;
    @TableField("live_studio_title")
    private String liveStudioTitle;
    @TableField("live_studio_status")
    private Integer liveStudioStatus;
    private Integer source;
    @TableField("anchor_name")
    private String anchorName;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
    @TableField("review_status")
    private Integer reviewStatus;
    @TableField("live_cover")
    private String liveCover;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("modified_time")
    private Date modifiedTime;
}
