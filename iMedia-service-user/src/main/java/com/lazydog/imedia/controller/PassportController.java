package com.lazydog.imedia.controller;

import com.lazydog.bo.RegistryLoginBo;
import com.lazydog.enums.UserStatus;
import com.lazydog.imedia.service.UserService;
import com.lazydog.imedia.api.controller.user.BaseController;
import com.lazydog.imedia.api.controller.user.PassportControllerApi;
import com.lazydog.pojo.AppUser;
import com.lazydog.result.GraceJSONResult;
import com.lazydog.result.JSONResult;
import com.lazydog.result.ResponseStatusEnum;
import com.lazydog.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController implements PassportControllerApi {

    @Autowired
    UserService userService;
    @Override
    public JSONResult getSMSCode(@RequestParam String mobile, HttpServletRequest request) {
        String userIP= IPUtil.getRequestIp(request);

        redis.setnx60s(MOBILE_SMSCODE+":"+userIP,userIP);

        String random=(int)((Math.random()*9+1)*100000)+"";

        redis.set(MOBILE_SMSCODE+":"+mobile,random,30*60);
        System.out.println(redis.get(MOBILE_SMSCODE+":"+mobile));

        return JSONResult.ok();
    }


    @Override
    public GraceJSONResult doLogin(RegistryLoginBo registryLoginBo, BindingResult result, HttpServletRequest request,HttpServletResponse response) {
        if (result.hasErrors()){
            Map<String, String> errors = getErrors(result);
            return GraceJSONResult.errorMap(errors);
        }
        String mobile = registryLoginBo.getMobile();
        String smsCode = registryLoginBo.getSmsCode();
        String redisSMScode = redis.get(MOBILE_SMSCODE + ":" + mobile);
        if (StringUtils.isEmpty(redisSMScode)||redisSMScode.equals(smsCode)==false){
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }
        AppUser user = userService.queryMobileIsExist(mobile);
        if (user!=null&&user.getActiveStatus()== UserStatus.FROZEN.type){
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_FROZEN);
        }else if (user==null){
            user = userService.createUser(mobile);
        }
        int activeStatus = user.getActiveStatus();
        if (activeStatus!=UserStatus.FROZEN.type){
            String token= UUID.randomUUID().toString();
            redis.set(REDIS_USER_TOKEN+":"+user.getId(),token);
            setCookie(request,response,"token",token,60000000);
            setCookie(request,response,"uid",user.getId(),600000);
        }
//        redis.del(MOBILE_SMSCODE + ":" + mobile);
        return GraceJSONResult.ok(activeStatus);
    }

    @PostMapping("/logout")
    public GraceJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        redis.del(REDIS_USER_TOKEN + ":" + userId);

        setCookie(request, response, "token", "", 0);
        setCookie(request, response, "uid", "", 0);

        return GraceJSONResult.ok();
    }




}
