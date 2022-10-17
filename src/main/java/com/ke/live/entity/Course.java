package com.ke.live.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("live_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 直播课程主键
     */
    @TableId(value = "courseId", type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程名称
     */
    @TableField("courseName")
    private String courseName;

    /**
     * 课程开始时间
     */
    @TableField("startTime")
    private LocalDateTime startTime;

    /**
     * 课程结束时间
     */
    @TableField("endTime")
    private LocalDateTime endTime;

    /**
     * 课程费用
     */
    @TableField("coursePrice")
    private BigDecimal coursePrice;

    /**
     * 课程介绍
     */
    @TableField("courseIntroduce")
    private String courseIntroduce;

    /**
     * 课程图片
     */
    @TableField("coursePhoto")
    private String coursePhoto;

    /**
     * 主讲人姓名
     */
    @TableField("speakerName")
    private String speakerName;

    /**
     * 主讲人(doctorId)
     */
    @TableField("speakerId")
    private Integer speakerId;

    /**
     * 机构
     */
    private Integer organ;

    /**
     * 第一职业点科室
     */
    private Integer depart;

    /**
     * 主讲人介绍
     */
    @TableField("speakerIntroduce")
    private String speakerIntroduce;

    /**
     * 主讲人照片
     */
    @TableField("speakerPhoto")
    private String speakerPhoto;

    /**
     * 主讲人照片类型(0默认1自定义)
     */
    @TableField("speakerPhotoType")
    private Boolean speakerPhotoType;

    /**
     * 报名人数
     */
    @TableField("signUpNum")
    private Integer signUpNum;

    /**
     * 虚拟报名人次(患者端展示使用)
     */
    @TableField("signUpVirtualNum")
    private Integer signUpVirtualNum;

    /**
     * 课程创建时间
     */
    @TableField("createDate")
    private LocalDateTime createDate;

    /**
     * 课程状态(0未开始 1进行中 2已结束)
     */
    @TableField("courseStatus")
    private Integer courseStatus;

    @TableField("lastModify")
    private LocalDateTime lastModify;

    /**
     * 开课前24小时提醒标记(0未提醒1已提醒)
     */
    @TableField("remindFlag")
    private Boolean remindFlag;

    /**
     * 邀请卡背景图
     */
    @TableField("invitationCardBackground")
    private String invitationCardBackground;

    /**
     * 邀请卡图片文件OSSId
     */
    @TableField("invitationCard")
    private String invitationCard;

    /**
     * 聊天室id
     */
    @TableField("sessionId")
    private String sessionId;

    /**
     * 课程类型(0 纳里系统课程 1第三方外部课程)
     */
    @TableField("courseType")
    private Boolean courseType;

    /**
     * 跳转链接
     */
    @TableField("linkUrl")
    private String linkUrl;

    /**
     * 课程审核标志(0:待审核 1:审核通过 2:审核未通过)
     */
    @TableField("auditStatus")
    private Boolean auditStatus;

    /**
     * 当前用户设备Id
     */
    @TableField("currentClient")
    private Integer currentClient;

    /**
     * base_clientconfig表Id
     */
    @TableField("baseClientId")
    private Integer baseClientId;

    /**
     * 直播类型(1线上图文直播 2线下直播 3视频直播)
     */
    @TableField("liveType")
    private Boolean liveType;

    /**
     * 草稿已读未读标记,0:未读 1:已读
     */
    @TableField("readStatus")
    private Boolean readStatus;

    /**
     * 视频直播是否存在录制(0未录制 1录制)
     */
    @TableField("recordingStatus")
    private Boolean recordingStatus;

    /**
     * 课程标签
     */
    @TableField("courseTab")
    private String courseTab;

    /**
     * 医生端限制报名人数
     */
    @TableField("limitSignUpNum")
    private Integer limitSignUpNum;


}
