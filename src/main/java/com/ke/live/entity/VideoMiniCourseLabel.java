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
 * 小程序视频标签表
 * </p>
 *
 * @author ${author}
 * @since 2022-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VideoMiniCourseLabel implements Serializable {

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
     * 视频号的视频feedId
     */
    private String feedId;

    /**
     * 标签编码
     */
    private String boxLink;

    /**
     * 标签名字
     */
    private String labelName;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


}
