package com.lazydog.imeadia.controller;

import com.lazydog.imeadia.mapper.AppUserMapper;
import com.lazydog.imeadia.mapper.FansMapper;
import com.lazydog.imedia.api.controller.user.HelloControllerApi;

import com.lazydog.pojo.AppUser;
import com.lazydog.pojo.Fans;
import com.lazydog.result.JSONResult;
import com.lazydog.utils.RedisOperator;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hi")
public class Hello implements HelloControllerApi {
    @Autowired
    AppUserMapper appUserMapper;
    @Autowired
    FansMapper fansMapper;
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
        List<AppUser>list=appUserMapper.selectAll();
//        for (AppUser appUser : list) {
//            System.out.println(appUser);
//        }
        return JSONResult.ok(list);
    }

    @GetMapping("/redis")
    public JSONResult redis(){
        redisOperator.set("age","99");
        return JSONResult.ok(redisOperator.get("age"));
    }
}
