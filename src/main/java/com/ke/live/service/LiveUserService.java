package com.ke.live.service;

import com.ke.live.entity.LiveUser;
import com.ke.live.mapper.LiveUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 小凡
 * @date 2022/2/24
 */
@Component
public class LiveUserService {

    private static final Logger logger = LoggerFactory.getLogger(LiveUserService.class);

    @Autowired
    private LiveUserMapper liveUserMapper;

    public boolean verifyPassword(String phone,String pwd){
        logger.info("phone=[{}] + pwd=[{}]",phone,pwd);
        LiveUser user = liveUserMapper.selectByUserId(phone);
        return user.getPassWord().equals(pwd);
    }
}
