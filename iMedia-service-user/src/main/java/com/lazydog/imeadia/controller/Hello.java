package com.lazydog.imeadia.controller;

import com.lazydog.imedia.api.controller.user.HelloControllerApi;

import com.lazydog.result.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hi")
public class Hello implements HelloControllerApi {
    @Override
    public String hello() {
        return "hello from api";
    }

    @Override
    public JSONResult result() {
        return JSONResult.ok("ok");
    }
}
