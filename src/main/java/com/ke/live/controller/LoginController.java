package com.ke.live.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ke.live.miniApp.MiniAppToken;
import com.ke.live.utils.HttpUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录后，立即获取小程序用户的基本信息：token及其有效时长，openid（唯一标识一位用户）。。。
 * @author 小凡
 */
@RestController
public class LoginController {

    //保存当前用户的token号和openid
    public static Map<String, String> LOGININFO;

    /**
     * 获取AccessToken，通常为2个小时，需要将其存储并定时更新
     * 获取openid
     *
     * @return token信息
     */
    @CrossOrigin(origins = {"*", "3600"})  //跨域注解，所有域名都可访问，且cookie的有效期为3600秒
    @GetMapping("/getAccessToken")
    public Map<String, String> getAccesToken(@RequestParam("code") String code) throws IOException {
        //存放所有返回消息（Token及其有效时长、openid）
        Map<String, String> results = new HashMap<>();

        Map<String, String> params = new HashMap<>();
        //小程序id
        params.put("APPID", "wx5cf231d4d4a01697");
        //小程序密码
        params.put("APPSECRET", "3ca5caf950967c1207e722f3ff890821");

        //访问接口，获取Access_Token和有效时长expires_in
        // RestTemplate来实现一个get请求，这样就可以轻松获取到access_token。
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);

        //接收返回的信息
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);


        results.put("Access_Token", object.getString("access_token"));
        //通常为2个小时，需要将其存储并定时更新
        results.put("expires_in", object.getString("expires_in"));


        //访问接口，获取openId
        String openidResult = "";
        try {//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            openidResult = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + params.get("APPID") + "&secret="
                            + params.get("APPSECRET") + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        MiniAppToken miniAppToken = mapper.readValue(openidResult, MiniAppToken.class);
        results.put("openId", miniAppToken.getOpenid());

        LOGININFO =results;

        return results;
    }


}
