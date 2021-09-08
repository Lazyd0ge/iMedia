package com.lazydog.imeadia.controller;

import com.lazydog.bo.RegistryLoginBo;
import com.lazydog.imedia.api.controller.user.BaseController;
import com.lazydog.imedia.api.controller.user.PassportControllerApi;
import com.lazydog.result.GraceJSONResult;
import com.lazydog.result.JSONResult;
import com.lazydog.result.ResponseStatusEnum;
import com.lazydog.utils.IPUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public GraceJSONResult doLogin(RegistryLoginBo registryLoginBo, BindingResult result) {
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
        return GraceJSONResult.ok();
    }




}
