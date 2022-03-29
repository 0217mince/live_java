package com.ke.live.controller;

import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.annotation.RestWrapper;
import com.ke.live.service.MiniProgramLiveOperateService;
import com.ke.live.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小凡
 */
@RestController
@RequestMapping("/live")
public class LiveController {
    @Autowired
    private MiniProgramLiveOperateService miniProgramLiveOperateService;

    @RequestMapping(value = "/searchInformation")
    @ResponseBody
    @RestWrapper
    public QueryResult<LiveMiniCourseDTO> searchLiveBroadcastInformation(LiveMiniCourseSearchDTO liveMiniCourseSearchDTO){
        return miniProgramLiveOperateService.findAllLiveSourceBySearchCriteria(liveMiniCourseSearchDTO);
    }
}
