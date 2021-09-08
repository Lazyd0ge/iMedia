package com.lazydog.imeadia.controller;

import com.lazydog.imedia.api.controller.user.BaseController;
import com.lazydog.imedia.api.controller.user.PassportControllerApi;
import com.lazydog.result.JSONResult;
import com.lazydog.utils.IPUtil;
import com.lazydog.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController implements PassportControllerApi {


    @Override
    public JSONResult getSMSCode(String mobile, HttpServletRequest request) {
        String userIP= IPUtil.getRequestIp(request);

        redis.setnx60s(MOBILE_SMSCODE+":"+userIP,userIP);

        String random=(int)((Math.random()*9+1)*100000)+"";

        redis.set(MOBILE_SMSCODE+":"+mobile,random,30*60);
        System.out.println(redis.get(MOBILE_SMSCODE+":"+mobile));

        return JSONResult.ok();
    }


}
