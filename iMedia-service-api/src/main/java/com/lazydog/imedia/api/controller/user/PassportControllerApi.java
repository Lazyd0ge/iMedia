package com.lazydog.imedia.api.controller.user;

import com.lazydog.bo.RegistryLoginBo;
import com.lazydog.result.GraceJSONResult;
import com.lazydog.result.JSONResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface PassportControllerApi {
    @GetMapping("getSMSCode")
    public JSONResult getSMSCode(String mobile, HttpServletRequest request);

    @PostMapping("doLogin")
    public GraceJSONResult doLogin(@Valid @RequestBody RegistryLoginBo registryLoginBo, BindingResult result);
}
