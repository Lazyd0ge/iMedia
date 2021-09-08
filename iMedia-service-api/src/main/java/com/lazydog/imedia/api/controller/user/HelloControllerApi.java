package com.lazydog.imedia.api.controller.user;


import com.lazydog.result.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;

public interface HelloControllerApi {
    @GetMapping("/hello")
    String hello();
    @GetMapping("/result")
    JSONResult result();
}
