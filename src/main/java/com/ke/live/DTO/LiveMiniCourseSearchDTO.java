package com.ke.live.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kzf
 * @date 16:53 2021/9/24
 */
@Data
public class LiveMiniCourseSearchDTO implements Serializable {

    private Integer roomId;
    private Date startTime;
    private Date endTime;
    private String anchorName;
    private String liveStudioTitle;
    private Integer start;
    private Integer limit;
}
