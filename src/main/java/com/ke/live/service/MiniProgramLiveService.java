package com.ke.live.service;

import com.alibaba.fastjson.JSONObject;
import com.ke.live.DTO.MiniProgramLiveDTO;
import com.ke.live.constant.LiveMiniCourseConstant;
import com.ke.live.controller.LoginController;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.mapper.LiveUserMapper;
import com.ke.live.miniApp.MiniAppAccessToken;
import com.ke.live.miniApp.MiniAppToken;
import com.ke.live.utils.HttpUtil;
import com.ke.live.utils.JSONUtils;
import com.ke.live.utils.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MiniProgramLiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniProgramLiveService.class);

    @Autowired
    private LiveMiniCourseMapper liveMiniCourseDAO;
    @Autowired
    private LiveUserMapper liveUserMapper;

    /**
     * 获取小程序直播数据的地址
     */
    private static final String GET_LIVE_INFO_URL = "https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";

    /**
     * 目前就一个纳里健康小程序
     */
    private static final String NALI_APPID = "wx597417e9f0895f8a";


    /**
     * 目前仅定时器调用，用于同步小程序直播的数据到数据库  半小时调用一次
     */
    public void syncMiniProgramLive(){
        List<MiniProgramLiveDTO> miniProgramLiveDTOS = new ArrayList<>();
        try {
            String response = syncMiniProgramLive(NALI_APPID);
            miniProgramLiveDTOS = JSONObject.parseArray(response,MiniProgramLiveDTO.class);
            if(ValidateUtil.blankList(miniProgramLiveDTOS)){
                LOGGER.error("syncMiniProgramLive error response[{}]",response);
                return;
            }
        }catch (Exception e){
            LOGGER.error("syncMiniProgramLive error",e);
            return;
        }
        LOGGER.info("syncMiniProgramLive start miniProgramLiveDTOS[{}]",miniProgramLiveDTOS.size());
        miniProgramLiveDTOS.stream().forEach(miniProgramLiveDTO -> {
            saveOrUploadLiveMiniCourse(miniProgramLiveDTO);
        });
    }

    private void saveOrUploadLiveMiniCourse(MiniProgramLiveDTO miniProgramLiveDTO){
        try {
            LiveMiniCourse liveMiniCourse = liveMiniCourseDAO.getLiveMiniCourseByRoomId(miniProgramLiveDTO.getRoomid());
            if (null == liveMiniCourse) {
                liveMiniCourse = new LiveMiniCourse();
                liveMiniCourse.setRoomId(miniProgramLiveDTO.getRoomid());
                String uuId = UUID.randomUUID().toString();
                String miniCourseId = uuId.replace("-","");
                liveMiniCourse.setLiveMiniCourseId(miniCourseId);
                liveMiniCourse.setLiveStudioTitle(miniProgramLiveDTO.getName());
                liveMiniCourse.setLiveStudioStatus(miniProgramLiveDTO.getLive_status());
                liveMiniCourse.setSource(LiveMiniCourseConstant.THIRD_LIVE_MINI);
                liveMiniCourse.setAnchorName(miniProgramLiveDTO.getAnchor_name());
                liveMiniCourse.setStartTime(new Date(miniProgramLiveDTO.getStart_time()*1000L));
                liveMiniCourse.setEndTime(new Date(miniProgramLiveDTO.getEnd_time()*1000L));
                liveMiniCourse.setReviewStatus(LiveMiniCourseConstant.THIRD_LIVE_REVIEW_NO);
                liveMiniCourse.setLiveCover(miniProgramLiveDTO.getCover_img());
                liveMiniCourse.setModifiedTime(new Date());
                liveMiniCourseDAO.insertOne(liveMiniCourse);
            } else {
                liveMiniCourse.setLiveStudioTitle(miniProgramLiveDTO.getName());
                liveMiniCourse.setLiveStudioStatus(miniProgramLiveDTO.getLive_status());
                liveMiniCourse.setAnchorName(miniProgramLiveDTO.getAnchor_name());
                liveMiniCourse.setStartTime(new Date(miniProgramLiveDTO.getStart_time()*1000L));
                liveMiniCourse.setEndTime(new Date(miniProgramLiveDTO.getEnd_time()*1000L));
                liveMiniCourse.setLiveCover(miniProgramLiveDTO.getCover_img());
                liveMiniCourse.setModifiedTime(new Date());
                liveMiniCourseDAO.update(liveMiniCourse);
            }
        }catch (Exception e){
            LOGGER.error("saveOrUploadLiveMiniCourse error miniProgramLiveDTO[{}]",JSONObject.toJSONString(miniProgramLiveDTO),e);
        }
    }

    /**
     * 同步小程序直播的信息
     * @param appId
     */
    public String syncMiniProgramLive(String appId){
        //获取小程序token
//        MiniAppToken miniAppToken = loginController.getAccesToken()

        String url = GET_LIVE_INFO_URL+ liveUserMapper.selectOpenIdByAppId(appId);
        //准备请求体
        Map<String,Integer> requestBody = new HashMap<>();
        requestBody.put("start",0);
        requestBody.put("limit",50);
        String response = HttpUtil.httpPostAsString(url, JSONUtils.toString(requestBody));
        JSONObject liveInfo = JSONObject.parseObject(response);
        if(ValidateUtil.notBlankString(liveInfo.getString("room_info"))){
            return liveInfo.getString("room_info");
        }else {
            LOGGER.error("getLiveInfo error response[{}]",liveInfo);
            return "";
        }
    }
}
