package com.ke.live.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author kzf
 * @date 2021/9/24
 */
@Data
public class LiveMiniCourseLabelDTO {
    private Integer id;
    private String liveMiniCourseId;
    private Integer roomId;
    private String liveLabelName;
    private String boxLink;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLiveMiniCourseId() {
        return liveMiniCourseId;
    }

    public void setLiveMiniCourseId(String liveMiniCourseId) {
        this.liveMiniCourseId = liveMiniCourseId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getLiveLabelName() {
        return liveLabelName;
    }

    public void setLiveLabelName(String liveLabelName) {
        this.liveLabelName = liveLabelName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getBoxLink() {
        return boxLink;
    }

    public void setBoxLink(String boxLink) {
        this.boxLink = boxLink;
    }

    @Override
    public String toString() {
        return "LiveMiniCourseLabelDTO{" +
                "id=" + id +
                ", liveMiniCourseId='" + liveMiniCourseId + '\'' +
                ", roomId=" + roomId +
                ", liveLabelName='" + liveLabelName + '\'' +
                ", boxLink='" + boxLink + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
