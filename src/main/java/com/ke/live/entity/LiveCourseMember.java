package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author kzf
 * @since 2022-07-07
 */
@Data
@TableName("live_course_member")
public class LiveCourseMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参加课程记录Id
     */
    @TableId(value = "courseMemberId", type = IdType.AUTO)
    private Integer courseMemberId;

    /**
     * 直播课程id
     */
    private Integer courseId;

    /**
     * 主讲人Id
     */
    private Integer speakerId;

    /**
     * 成员角色(0普通成员 1 助理 2主讲人
)
     */
    private Boolean memberType;

    /**
     * 医生主键或者患者主键
     */
    private String memberId;

    /**
     * 成员姓名
     */
    private String memberName;

    /**
     * 成员头像
     */
    private String memberPhoto;

    /**
     * 实际支付价格（优惠后价格）
     */
    private BigDecimal actualPrice;

    /**
     * 付费状态(0待付费,1付费，2退款中，3退款成功，4退款失败)
     */
    private Boolean payFlag;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 支付方式
     */
    private String payWay;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付平台分配的机构id
     */
    private String payOrganId;

    /**
     * 业务单创建时间
     */
    private LocalDateTime createDate;

    /**
     * 支付时间
     */
    private LocalDateTime paymentDate;

    /**
     * 是否提醒(0未提醒1已提醒)
     */
    private Boolean remindFlag;

    /**
     * 是否已跟医生结算(0未结算 1已结算)
     */
    private Boolean settlementFlag;

    /**
     * 结算时间
     */
    private LocalDateTime settlementDate;

    /**
     * 设备Id
     */
    private Integer deviceId;

    /**
     * 报名状态(0 课程申请待支付   1参与课程中    2 课程结束    3课程申请被拒绝  9课程申请取消)
     */
    private Boolean memberStatus;

    private LocalDateTime lastModify;

    /**
     * 原因
     */
    private String cause;

    /**
     * 优惠券id
     */
    private Integer couponId;

    /**
     * 优惠券描述，用于前端展示
     */
    private String couponName;

    /**
     * 是否听课(0开课后未进入 1开课后进入过 2课程结束后进入)
     */
    private Boolean listenFlag;

    /**
     * 存储该成员的Urt信息
     */
    private Integer memberUrt;

    /**
     * 患者是否被禁言  0未禁言 1已禁言
     */
    private Boolean canSpeaking;

    /**
     * 禁言管理员id 可能是医生id   课程助理mpiid
     */
    private String gagAdminId;

    /**
     * base_clientconfig表Id
     */
    private Integer baseClientId;

    /**
     * 关闭弹框标志0未关闭1已关闭一个2关闭两个3关闭三个
     */
    private Boolean closeStatus;

    /**
     * 签到时间
     */
    private LocalDateTime signInTime;

    /**
     * 机构类型，0平台，1机构，2药企
     */
    private Integer organPayType;

    /**
     * 是否删除，0 否 1是
     */
    private Boolean delFlag;

}
