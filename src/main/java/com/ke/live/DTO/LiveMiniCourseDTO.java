package com.ke.live.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author kzf
 * @date 2021/9/22
 */
@Data
public class LiveMiniCourseDTO implements Serializable {
    private static final long serialVersionUID = -5015301472840699448L;
    private Integer id;
    private String liveMiniCourseId;
    private Integer roomId;
    private String liveStudioTitle;
    private Integer liveStudioStatus;
    private String liveStudioStatusText;
    private Integer source;
    private String anchorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private Integer reviewStatus;
    private String liveCover;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;
    private Integer reservationStatus;
    private List<LiveMiniCourseLabelDTO> liveMiniCourseLabelDTOList;
}
