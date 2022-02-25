package com.ke.live.constant;

/**
 * @author yuanb
 * @description
 * @Date 15:25 2021/9/23
 * @Modified By:
 */
public class LiveMiniCourseConstant {
    /**
     *直播来源，0表示微信小程序直播，1表示视频号直播
     */
    public static final Integer THIRD_LIVE_MINI = 0;
    public static final Integer THIRD_LIVE_SHIPINHAO = 1;

    /**
     * 第三方直播的审核状态
     */
    public static final Integer THIRD_LIVE_REVIEW_YES = 1;
    public static final Integer THIRD_LIVE_REVIEW_NO = 0;

    /**
     * 101：直播中
     * 102：未开始
     * 103：已结束
     * 104：禁播
     * 105：暂停
     * 106：异常
     * 107：已过期
     */
    public static final Integer LIVE_BROADCAST = 101;
    public static final Integer NOT_STARTED = 102;
    public static final Integer HSA_ENDED = 103;
    public static final Integer NO_BROADCASTING = 104;
    public static final Integer SUSPEND = 105;
    public static final Integer ABNORMAL = 106;
    public static final Integer EXPIRED = 107;

    /**
     * 第三方视频的审核状态
     */
    public static final Integer THIRD_VIDEO_REVIEW_YES = 1;
    public static final Integer THIRD_VIDEO_REVIEW_NO = 0;

    /**
     * 热点推荐的审核状态
     */
    public static final Integer HOTSPOT_REVIEW_YES = 1;
    public static final Integer HOTSPOT_REVIEW_NO = 0;

}
