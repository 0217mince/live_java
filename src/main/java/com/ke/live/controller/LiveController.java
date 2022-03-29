package com.ke.live.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.DTO.ScratchableBoxDTO;
import com.ke.live.annotation.RestWrapper;
import com.ke.live.entity.ScratchableBox;
import com.ke.live.mapper.ScratcahableBoxMapper;
import com.ke.live.service.MiniProgramLiveOperateService;
import com.ke.live.utils.BeanUtils;
import com.ke.live.utils.ObjectCopyUtils;
import com.ke.live.utils.QueryResult;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 小凡
 */
@RestController
@RequestMapping("/live")
public class LiveController {
    @Autowired
    private MiniProgramLiveOperateService miniProgramLiveOperateService;
    @Autowired
    private ScratcahableBoxMapper scratcahableBoxMapper;

    @RequestMapping(value = "/searchInformation")
    @ResponseBody
    @RestWrapper
    public QueryResult<LiveMiniCourseDTO> searchLiveBroadcastInformation(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO){
        return miniProgramLiveOperateService.findAllLiveSourceBySearchCriteria(liveMiniCourseSearchDTO);
    }

    @RequestMapping(value = "/auditLive")
    @ResponseBody
    @RestWrapper
    public boolean auditLive(Integer roomId) throws Exception {
        miniProgramLiveOperateService.auditLiveToUpdateReviewStatus(roomId);
        return true;
    }

    @RequestMapping(value = "/tagLibrary")
    @ResponseBody
    @RestWrapper
    public List<ScratchableBoxDTO> tagLibrary(String userName) throws JsonProcessingException {
        return ObjectCopyUtils.convert(scratcahableBoxMapper.findAllByBelonging(userName),ScratchableBoxDTO.class);
    }

    @RequestMapping(value = "/newLabel")
    @ResponseBody
    @RestWrapper
    public void newLabel(ScratchableBox scratchableBox){
        scratcahableBoxMapper.insert(scratchableBox);
    }
}
