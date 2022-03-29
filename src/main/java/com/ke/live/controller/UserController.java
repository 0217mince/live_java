package com.ke.live.controller;

import com.ke.live.annotation.RestWrapper;
import com.ke.live.service.LiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 小凡
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LiveUserService liveUserService;

    @RequestMapping(value = "/login")
    @ResponseBody
    @RestWrapper
    public boolean login(String userName, String passWord) throws Exception {
        return liveUserService.verifyPassword(userName,passWord);
    }
}
