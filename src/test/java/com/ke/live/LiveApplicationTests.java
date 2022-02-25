package com.ke.live;

import com.ke.live.DTO.LiveMiniCourseSearchDTO;
import com.ke.live.entity.LiveMiniCourse;
import com.ke.live.entity.LiveUser;
import com.ke.live.mapper.LiveMiniCourseMapper;
import com.ke.live.mapper.LiveUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    LiveMiniCourseMapper liveMiniCourseMapper;
    @Autowired
    LiveUserMapper liveUserMapper;

    @Test
    void contextLoads() {

//        List<LiveMiniCourse> liveMiniCourseList = liveMiniCourseMapper.findAll();
        LiveMiniCourseSearchDTO liveMiniCourseSearchDTO = new LiveMiniCourseSearchDTO();
//        liveMiniCourseSearchDTO.setRoomId(2);
//        liveMiniCourseSearchDTO.setAnchorName("吴主任");
//        liveMiniCourseSearchDTO.setLiveStudioTitle("牙医直播");
        liveMiniCourseSearchDTO.setStartTime(new Date());
        liveMiniCourseSearchDTO.setEndTime(new Date());
        List<LiveMiniCourse> liveMiniCourseList = liveMiniCourseMapper.findLiveMiniCourseByOperateDTO(liveMiniCourseSearchDTO);
        for (LiveMiniCourse liveMiniCourse: liveMiniCourseList
        ) {
            System.out.println(liveMiniCourse.toString());
        }
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

}
