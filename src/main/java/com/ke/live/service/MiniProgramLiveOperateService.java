package com.ke.live.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.DTO.LiveMiniCourseLabelDTO;
import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.constant.LiveMiniCourseConstant;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveMiniCourseLabel;
import com.ke.live.mapper.LiveMiniCourseLabelMapper;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.utils.BeanUtils;
import com.ke.live.utils.ObjectCopyUtils;
import com.ke.live.utils.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kzf
 * @date 2021/9/23
 */
@Service
public class MiniProgramLiveOperateService {

    @Autowired
    private LiveMiniCourseMapper liveMiniCourseDAO;
    @Autowired
    private LiveMiniCourseLabelMapper liveMiniCourseLabelDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniProgramLiveOperateService.class);

    /**
     * 运营平台根据搜索提交件分页查询所有直播信息
     */
    public QueryResult<LiveMiniCourseDTO> findAllLiveSourceBySearchCriteria(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO){
        LOGGER.info("findAllLiveSourceBySearchCriteria liveMiniCourseOperate[{}]", JSONObject.toJSONString(liveMiniCourseSearchDTO));
        QueryResult<LiveMiniCourseDTO> result = new QueryResult<>();
        System.out.println(liveMiniCourseSearchDTO.toString());
        List<LiveMiniCourse> liveMiniCourses = liveMiniCourseDAO.findLiveMiniCourseByOperateDTO(liveMiniCourseSearchDTO);
        List<LiveMiniCourseDTO> liveMiniCourseDTOList = new ArrayList<>();
        liveMiniCourses.forEach(
                liveMiniCourse -> {
                    LiveMiniCourseDTO liveMiniCourseDTO = BeanUtils.map(liveMiniCourse, LiveMiniCourseDTO.class);
                    //将直播状态归纳成运营平台需要展示的状态
                    liveMiniCourseDTO.setLiveStudioStatus(sortLiveStatus(liveMiniCourse.getLiveStudioStatus()));
                    //读取这个直播标签对象
                    try {
                        liveMiniCourseDTO.setLiveMiniCourseLabelDTOList(ObjectCopyUtils.convert(liveMiniCourseLabelDAO.findLiveLabelByRoomId(liveMiniCourse.getRoomId()),LiveMiniCourseLabelDTO.class));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    liveMiniCourseDTOList.add(liveMiniCourseDTO);
                }
        );
        result.setTotal(liveMiniCourseDAO.getLiveMiniCourseCountByOperateDTO(liveMiniCourseSearchDTO));
        result.setItems(liveMiniCourseDTOList);
        return result;
    }

    /**
     * 运营平台审核直播操作
     */
    public void auditLiveToUpdateReviewStatus(Integer roomId){
        if (liveMiniCourseDAO.getLiveMiniCourseByRoomId(roomId).getReviewStatus().equals(LiveMiniCourseConstant.THIRD_LIVE_REVIEW_NO)){
            //将审核状态更新为已审核，0为未审核，1为已审核
            liveMiniCourseDAO.updateReviewStatusByRoomId(roomId,LiveMiniCourseConstant.THIRD_LIVE_REVIEW_YES);
        }else {
            //将审核状态更新为未审核，下线直播
            liveMiniCourseDAO.updateReviewStatusByRoomId(roomId,LiveMiniCourseConstant.THIRD_LIVE_REVIEW_NO);
        }
    }

    /**
     * 运营平台保存直播标签操作
     */
    public void updateLiveTag(List<LiveMiniCourseLabelDTO> liveMiniCourseLabelDTOList, Integer roomId, String liveMiniCourseId){
        LOGGER.info("updateLiveTag start");
        liveMiniCourseLabelDAO.deleteLiveLabelByRoomId(roomId);
        LiveMiniCourseLabel liveMiniCourseLabel = new LiveMiniCourseLabel();
        for (LiveMiniCourseLabelDTO liveMiniCourseLabelDTO:liveMiniCourseLabelDTOList){
            liveMiniCourseLabel.setLiveLabelName(liveMiniCourseLabelDTO.getLiveLabelName());
            liveMiniCourseLabel.setBoxLink(liveMiniCourseLabelDTO.getBoxLink());
            liveMiniCourseLabel.setRoomId(roomId);
            liveMiniCourseLabel.setLiveMiniCourseId(liveMiniCourseId);
            liveMiniCourseLabel.setCreatedTime(new Date());
            liveMiniCourseLabelDAO.save(liveMiniCourseLabel);
        }
    }

    /**
     * 转换成运营平台规定的直播状态
     */
    public Integer sortLiveStatus(Integer liveStudioStatus){
        if (liveStudioStatus.equals(LiveMiniCourseConstant.NO_BROADCASTING) || liveStudioStatus.equals(LiveMiniCourseConstant.SUSPEND) ||liveStudioStatus.equals(LiveMiniCourseConstant.ABNORMAL)){
            //直播状态，异常
            return LiveMiniCourseConstant.NO_BROADCASTING;
        } else {
            return liveStudioStatus;
        }
    }
}
