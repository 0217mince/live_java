package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * <p>
 * 小程序热点推荐表
 * </p>
 *
 * @author kzf
 * @since 2022-07-07
 */
@Data
@TableName("hotspot_mini_course")
public class HotspotMiniCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 热点表Id
     */
    private String hotspotMiniCourseId;

    /**
     * 热点类型，0：直播，1：视频，2：文章
     */
    private Integer hotspoType;

    /**
     * 热点来源，0：微信小程序直播，1：视频号，2：公众号
     */
    private Integer hotspotSource;

    /**
     * 热点所属
     */
    private String hotspotBelonging;

    /**
     * 热点标题
     */
    private String hotspotTitle;

    /**
     * 热点id（视频feedID、直播房间号、文章ID）
     */
    private String hotspotId;

    /**
     * 审核状态，0：待审核；1：已审核
     */
    private Integer reviewStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 排序值
     */
    private Integer sortValue;

}
