package com.lazydog.imedia.api.controller.user;

import com.lazydog.result.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public interface PassportControllerApi {
    @GetMapping("getSMSCode")
    public JSONResult getSMSCode(String mobile, HttpServletRequest request);
}
