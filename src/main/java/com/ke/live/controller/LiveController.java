package com.ke.live.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ke.live.DTO.LiveMiniCourseDTO;
import com.ke.live.DTO.LiveMiniCourseLabelDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public boolean newLabel(ScratchableBox scratchableBox){
        System.out.println(scratchableBox.toString());
        if (null == scratchableBox.getCategoryId()){
            scratchableBox.setCreateDate(new Date());
            scratchableBox.setLastModify(new Date());
            scratcahableBoxMapper.insert(scratchableBox);
        }else {
            scratchableBox.setLastModify(new Date());
            scratcahableBoxMapper.updateLabel(scratchableBox);
        }
        return true;
    }

    @RequestMapping(value = "/deleteLabel")
    @ResponseBody
    @RestWrapper
    public boolean deleteLabel(Integer categoryId){
        scratcahableBoxMapper.deleteById(categoryId);
        return true;
    }

    @RequestMapping(value = "/updateLabel")
    @ResponseBody
    @RestWrapper
    public void updateLabel(ScratchableBox scratchableBox){
        scratchableBox.setLastModify(new Date());
        scratcahableBoxMapper.updateLabel(scratchableBox);
    }

    @RequestMapping(value = "/updateLiveTag")
    @ResponseBody
    @RestWrapper
    public boolean updateLiveTag(@RequestBody JSONObject jsonParam){
        List<Integer> list = (List<Integer>) jsonParam.get("categoryIds");
        Integer roomId = (Integer) jsonParam.get("roomId");
        String liveMiniCourseId = (String) jsonParam.get("liveMiniCourseId");
        if (null == list){
            System.out.println("=====>>>>>");
        }
        miniProgramLiveOperateService.updateLiveTag(list,roomId,liveMiniCourseId);
        return true;
    }
}
