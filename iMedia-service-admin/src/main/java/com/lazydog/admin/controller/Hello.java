package com.lazydog.admin.controller;

import com.lazydog.imedia.api.controller.user.HelloControllerApi;

import com.lazydog.pojo.AppUser;
import com.lazydog.result.JSONResult;
import com.lazydog.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hi")
public class Hello implements HelloControllerApi {

    @Autowired
    RedisOperator redisOperator;
    @Override
    public String hello() {
        return "hello from api";
    }

    @Override
    public JSONResult result() {
        return JSONResult.ok("ok");
    }

    @Override
    public JSONResult all() {
        return null;
    }
}
