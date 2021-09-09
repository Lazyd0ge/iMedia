package com.lazydog.imedia.api.controller.user;

import com.lazydog.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE="mobile:smscode";
    public static final String REDIS_USER_TOKEN="redis_user_token";


    public Map<String ,String> getErrors(BindingResult result){
        Map<String ,String> map=new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            String field = error.getField();
            String msg = error.getDefaultMessage();
            map.put(field,msg);
        }
        return map;
    }

    public void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,Integer maxAge){
        try {
            cookieValue= URLEncoder.encode(cookieValue,"utf-8");
            Cookie cookie=new Cookie(cookieName,cookieValue);
            cookie.setMaxAge(maxAge);
//            cookie.setDomain("127.0.0.1");
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
