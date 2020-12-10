package com.unluckyworker.appointment.controller;


import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.utils.HttpClientUtils;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@Data
@ConfigurationProperties(prefix = "oauth")
public class WXController {

    @Value("${oauth.wx.appid}")
    private String appid;

    @Value("${oauth.wx.appsecret}")
    private String appsecret;

    @Value("${oauth.callback.https}")
    private String https;

    private String access_Token;
    private String openID;

    @GetMapping("/wx/checkSignature")
    public boolean checkSignature(HttpServletRequest request){

        String signature = request.getHeader("signature");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String token = "wxtoken";
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(nonce);
        list.add(timestamp);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
        }
        String res = DigestUtils.sha1Hex(sb.toString());
        return res.equals(signature);
    }

    @GetMapping("/wx/login")
    public String wxlogin() {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + appid +
                "&redirect_uri=" + https +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        return "redirect_uri:" + url;
    }

    @GetMapping("/wx/callback")
    public String wxcallback(String code, ModelMap map) throws IOException {
        // 第二步：通过code换取网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +
                "&secret=" + appsecret +
                "&code=" + code +
                "&grant_type=authorization_code";

        JSONObject jsonObject = HttpClientUtils.doGet(url);

        openID = jsonObject.getString("openid");
        access_Token = jsonObject.getString("access_token");

        System.out.println("access_Token" + access_Token);

        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_Token +
                "&openid=" + openID +
                "&lang=zh_CN";
        JSONObject userInfoJson = HttpClientUtils.doGet(url);
        System.out.println("UserInfo:" + userInfoJson);


        // 微信帐号做来一个关联，来关联我们的账号体系
        // 此处实现自己的保存用户信息逻辑
        return "redirect:/gohome?openid=" + openID;
    }

    @GetMapping("/wx/gohome")
    public String gohome(String openID){
        return openID;
    }

}
