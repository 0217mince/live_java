package com.ke.live.service;

import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.constant.LiveMiniCourseConstant;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveReservationRecord;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.mapper.LiveReservationRecordMapper;
import com.ke.live.utils.BeanUtils;
import com.ke.live.utils.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kzf
 * @date 2021/10/15
 */
@Service
public class LiveMiniCourseUnLoginService {
    @Autowired
    private LiveMiniCourseMapper liveMiniCourseDAO;
    @Autowired
    private LiveReservationRecordMapper liveReservationRecordDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LiveMiniCourseUnLoginService.class);

    /**
     *获取微信小程序首页直播推荐入口信息
     */
    public LiveMiniCourseDTO getLiveEntrance(){
        List<LiveMiniCourse> liveMiniCourses = liveMiniCourseDAO.findOneLiveSource();
        if(ValidateUtil.blankList(liveMiniCourses)){
            return new LiveMiniCourseDTO();
        }
        return BeanUtils.map(liveMiniCourseDAO.findOneLiveSource().get(0), LiveMiniCourseDTO.class);
    }

    /**
     * 根据直播标签查找相应的直播
     */
    public List<LiveMiniCourseDTO> findLiveSourceByLabel(String boxLink, Integer startIndex, Integer pageSize){
        LOGGER.info("findLiveSourceByLabel ==>> boxLink[{}]",boxLink);
        List<LiveMiniCourse> liveMiniCourseList;
        if (ValidateUtil.blankString(boxLink)){
            //如没有传入筛选标签，则返回全部符合已审核的直播
            liveMiniCourseList = liveMiniCourseDAO.findAllLiveMiniCourseByReviewStatus(LiveMiniCourseConstant.THIRD_LIVE_REVIEW_YES);
        }else {
            liveMiniCourseList = liveMiniCourseDAO.findLiveMiniCourseByLiveLabel(boxLink,startIndex,pageSize);
        }
        List<LiveMiniCourseDTO> liveMiniCourseDTOList = new ArrayList<>();
        if (ValidateUtil.blankList(liveMiniCourseList)){
            return liveMiniCourseDTOList;
        }
        for (LiveMiniCourse liveMiniCourse : liveMiniCourseList){
            LiveMiniCourseDTO liveMiniCourseDTO = BeanUtils.map(liveMiniCourse, LiveMiniCourseDTO.class);
            //更新DTO预约状态
            LiveReservationRecord liveReservationRecord = liveReservationRecordDAO.getLiveReservationRecordByRoomIdAndUserId(liveMiniCourse.getRoomId(), "");
            if (liveReservationRecord == null){
                //没有找到预约记录就把预约状态设置为0，未预约
                liveMiniCourseDTO.setReservationStatus(0);
            }else {
                liveMiniCourseDTO.setReservationStatus(liveReservationRecord.getReservationStatus());
            }
            liveMiniCourseDTOList.add(liveMiniCourseDTO);
        }
        return liveMiniCourseDTOList;
    }
}
