package com.ke.live;

import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.config.DataSourceConfiguration;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveUser;
import com.ke.live.entity.ScratchableBox;
import com.ke.live.mapper.*;
import com.ke.live.service.LiveMiniCourseService;
import com.ke.live.service.LiveMiniCourseUnLoginService;
import com.ke.live.service.MiniProgramLiveOperateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    LiveMiniCourseMapper liveMiniCourseMapper;
    @Autowired
    LiveUserMapper liveUserMapper;
    @Autowired
    ScratcahableBoxMapper scratcahableBoxMapper;
    @Autowired
    MiniProgramLiveOperateService miniProgramLiveOperateService;

    @Autowired
    VideoMiniCourseMapper videoMiniCourseMapper;
    @Autowired
    HotspotMiniCourseMapper hotspotMiniCourseMapper;
    @Autowired
    LiveMiniCourseUnLoginService liveMiniCourseUnLoginService;

    @Test
    void contextLoads() {

//        List<LiveMiniCourse> liveMiniCourseList = liveMiniCourseMapper.findAll();
        LiveMiniCourseSearchDTO liveMiniCourseSearchDTO = new LiveMiniCourseSearchDTO();
//        liveMiniCourseSearchDTO.setRoomId(2);
        liveMiniCourseSearchDTO.setAnchorName("吴主任");
//        liveMiniCourseSearchDTO.setLiveStudioTitle("牙医直播");
//        liveMiniCourseSearchDTO.setStartTime(new Date());
//        liveMiniCourseSearchDTO.setEndTime(new Date());
        List<LiveMiniCourse> liveMiniCourseList = liveMiniCourseMapper.findLiveMiniCourseByOperateDTO(liveMiniCourseSearchDTO);
        for (LiveMiniCourse liveMiniCourse: liveMiniCourseList) {
            System.out.println(liveMiniCourse.toString());
        }
        System.out.println(liveMiniCourseList.size());
    }

    @Test
    void testUser(){
        LiveUser liveUser = new LiveUser();
        liveUser.setUserId("1");
        liveUser.setAppId("kzf");
        liveUser.setOpenId("123");
        liveUser.setSessionKey("456");
        liveUser.setPassWord("abc");
        liveUserMapper.save(liveUser);
    }

    @Test
    void testDate(){
        Date date1 = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, -5);
        if (date1.before(nowTime.getTime())){
            System.out.println("1");
        }else {
            System.out.println("12");
        }
    }
    @Test
    void testLabel(){
        List<ScratchableBox> scratchableBoxList = scratcahableBoxMapper.findAllByBelonging("admin");
        scratchableBoxList.forEach(
                scratchableBox -> {
                    System.out.println(scratchableBox.toString());
                }
        );
    }
    @Test
    void testAddLabel(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        miniProgramLiveOperateService.updateLiveTag(list,1,"11");
    }

    @Test
    void testLabel1(){
        System.out.println(DataSourceConfiguration.URL);
    }

    @Test
    void testLabel2(){
        System.out.println(liveMiniCourseUnLoginService.getLiveEntrance());
    }
}
