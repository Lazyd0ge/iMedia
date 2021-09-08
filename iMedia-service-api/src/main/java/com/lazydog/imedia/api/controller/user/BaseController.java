package com.lazydog.imedia.api.controller.user;

import com.lazydog.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE="mobile:smscode";
}
