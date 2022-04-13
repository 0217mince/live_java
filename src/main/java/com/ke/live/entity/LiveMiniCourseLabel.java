package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName(value = "live_mini_course_label")
public class LiveMiniCourseLabel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("live_mini_course_id")
    private String liveMiniCourseId;
    @TableField("room_id")
    private Integer roomId;
    @TableField("live_label_name")
    private String liveLabelName;
    @TableField("box_link")
    private String boxLink;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("created_time")
    private Date createdTime;
}
