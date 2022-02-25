package com.ke.live.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 小凡
 */
@Data
public class MiniProgramLiveDTO implements Serializable {

    private static final long serialVersionUID = -8821432702225774578L;
    private String anchor_name;
    private Integer close_comment;
    private Integer close_goods;
    private Integer close_kf;
    private Integer close_like;
    private Integer close_replay;
    private String cover_img;
    private String creater_openid;
    private Long end_time;
    private String feeds_img;
    private Integer is_feeds_public;
    private Integer live_status;
    private Integer live_type;
    private String name;
    private Integer roomid;
    private String share_img;
    private Long start_time;

}
