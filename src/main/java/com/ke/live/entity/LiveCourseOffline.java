package com.ke.live.entity;

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
public class LiveCourseOffline implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程表的主键
     */
    @TableField("courseId")
    private Integer courseId;

    /**
     * 课程地址
     */
    private String address;

    /**
     * 课程详细地址
     */
    @TableField("detailAddress")
    private String detailAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 范围签到标志 0：无需范围签到  1：需要范围签到
     */
    @TableField("rangeSignFlag")
    private Boolean rangeSignFlag;

    /**
     * 课程状态 0：无效  1：有效
     */
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 最后一次修改时间
     */
    @TableField("lastModify")
    private LocalDateTime lastModify;

    /**
     * 签到范围
     */
    @TableField("signRange")
    private Integer signRange;


}
