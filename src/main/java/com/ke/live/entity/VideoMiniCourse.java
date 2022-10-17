package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 小程序视频表
 * </p>
 *
 * @author ${author}
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VideoMiniCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 视频表Id
     */
    private String videoId;

    /**
     * 视频标题
     */
    private String videoTitle;

    /**
     * 视频号的视频feedID
     */
    private String feedId;

    /**
     * 视频来源，0：纳里健康，1：纳里科普
     */
    private String videoSource;

    /**
     * 视频图片
     */
    private String videoPicture;

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
