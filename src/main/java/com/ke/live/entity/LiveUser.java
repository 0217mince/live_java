package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小凡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "live_user")
public class LiveUser {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField("user_Id")
    private String userId;
    @TableField("open_id")
    private String openId;
    @TableField("session_key")
    private String sessionKey;
    @TableField("app_id")
    private String appId;
    @TableField("pass_word")
    private String passWord;
}
