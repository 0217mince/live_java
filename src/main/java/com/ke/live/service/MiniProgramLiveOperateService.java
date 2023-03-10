package com.ke.live.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.DTO.LiveMiniCourseLabelDTO;
import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.constant.LiveMiniCourseConstant;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveMiniCourseLabel;
import com.ke.live.entity.ScratchableBox;
import com.ke.live.mapper.LiveMiniCourseLabelMapper;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.mapper.ScratcahableBoxMapper;
import com.ke.live.utils.BeanUtils;
import com.ke.live.utils.ObjectCopyUtils;
import com.ke.live.utils.QueryResult;
import com.ke.live.utils.ValidateUtil;
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
    @Autowired
    private ScratcahableBoxMapper scratcahableBoxMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniProgramLiveOperateService.class);

    /**
     * 运营平台根据搜索提交件分页查询所有直播信息
     * @param liveMiniCourseSearchDTO 搜索条件{
     *                                roomId：直播间ID
     *                                startTime：直播开始时间
     *                                endTime：直播结束时间
     *                                anchorName：直播名称
     *                                page：分页位置
     *                                size：分页大小
     * }
     * @return {
     *     totalCount：查询的总条数
     *     start：分页位置
     *     limit：分页大小
     *     records：短视频、直播信息
     * }
     */
    public QueryResult<LiveMiniCourseDTO> findAllLiveSourceBySearchCriteria(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO){
        LOGGER.info("findAllLiveSourceBySearchCriteria liveMiniCourseOperate[{}]", JSONObject.toJSONString(liveMiniCourseSearchDTO));
        liveMiniCourseSearchDTO.setPage(liveMiniCourseSearchDTO.getPage()*liveMiniCourseSearchDTO.getSize());
        QueryResult<LiveMiniCourseDTO> result = new QueryResult<>();
        System.out.println(liveMiniCourseSearchDTO.toString());
        List<LiveMiniCourse> liveMiniCourses = liveMiniCourseDAO.findLiveMiniCourseByOperateDTO(liveMiniCourseSearchDTO);
        List<LiveMiniCourseDTO> liveMiniCourseDTOList = new ArrayList<>();
        liveMiniCourses.forEach(
                liveMiniCourse -> {
                    LiveMiniCourseDTO liveMiniCourseDTO = BeanUtils.map(liveMiniCourse, LiveMiniCourseDTO.class);
                    liveMiniCourseDTO.setLiveStudioStatusText(getStatusText(liveMiniCourseDTO.getLiveStudioStatus()));
                    //将直播状态归纳成运营平台需要展示的状态
                    liveMiniCourseDTO.setLiveStudioStatus(sortLiveStatus(liveMiniCourse.getLiveStudioStatus()));
                    //读取这个直播标签对象
                    try {
                        List<LiveMiniCourseLabel> liveMiniCourseLabelList = liveMiniCourseLabelDAO.findLiveLabelByRoomId(liveMiniCourse.getRoomId());
                        liveMiniCourseDTO.setLiveMiniCourseLabelDTOList(ObjectCopyUtils.convert(liveMiniCourseLabelList,LiveMiniCourseLabelDTO.class));
                        StringBuilder stringBuilder = new StringBuilder();
                        List<Integer> list = new ArrayList<>(liveMiniCourseLabelList.size());
                        liveMiniCourseDTO.getLiveMiniCourseLabelDTOList().forEach(
                                liveMiniCourseLabelDTO -> {
                                    stringBuilder.append(liveMiniCourseLabelDTO.getLiveLabelName()+",");
                                    list.add(scratcahableBoxMapper.selectByKey(liveMiniCourseLabelDTO.getBoxLink()).getCategoryId());
                                }
                        );
                        if (ValidateUtil.notBlankString(stringBuilder.toString())){
                            liveMiniCourseDTO.setLabelNameList(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
                        }
                        liveMiniCourseDTO.setLabelList(list);
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
     * @param roomId 直播间ID
     */
    public void auditLiveToUpdateReviewStatus(Integer roomId) throws Exception {
        LOGGER.info("auditLiveToUpdateReviewStatus roomIf:[{}]",roomId);
        if (ValidateUtil.nullOrZeroInteger(roomId)){
            throw new Exception("直播房间号不存在！");
        }
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
     * @param roomId 直播间ID
     * @param liveMiniCourseId 短视频、直播表主键ID
     * @param categoryIds 标签信息{
     *                                   id：主键ID
     *                                   liveMiniCourseId：短视频、直播信息表主键
     *                                   roomId：直播间ID
     *                                   liveLabelName：标签名字
     *                                   boxLink：直播标签key
     *                                   createdTime：创建时间
     * }
     */
    public void updateLiveTag(List<Integer> categoryIds, Integer roomId, String liveMiniCourseId){
        LOGGER.info("updateLiveTag start");
        liveMiniCourseLabelDAO.deleteLiveLabelByRoomId(roomId);
        for (Integer categoryId:categoryIds){
            LiveMiniCourseLabel liveMiniCourseLabel = new LiveMiniCourseLabel();
            ScratchableBox scratchableBox = scratcahableBoxMapper.selectById(categoryId);
            liveMiniCourseLabel.setLiveLabelName(scratchableBox.getCategoryName());
            liveMiniCourseLabel.setBoxLink(scratchableBox.getLinkBox());
            liveMiniCourseLabel.setRoomId(roomId);
            liveMiniCourseLabel.setLiveMiniCourseId(liveMiniCourseId);
            liveMiniCourseLabel.setCreatedTime(new Date());
            liveMiniCourseLabelDAO.insert(liveMiniCourseLabel);
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

    public String getStatusText(Integer status){
        String statusText = null;
        switch (status){
            case 101:
                statusText = "直播中";
                break;
            case 102:
                statusText = "未开始";
                break;
            case 103:
                statusText = "已结束";
                break;
            case 104:
                statusText = "禁播";
                break;
            case 105:
                statusText = "暂停";
                break;
            case 106:
                statusText = "异常";
                break;
            case 107:
                statusText = "已过期";
                break;
            default:

        }
        return statusText;
    }
}
