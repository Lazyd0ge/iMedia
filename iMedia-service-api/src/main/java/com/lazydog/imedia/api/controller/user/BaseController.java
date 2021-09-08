package com.lazydog.imedia.api.controller.user;

import com.lazydog.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    @Autowired
    public RedisOperator redis;

    public static final String MOBILE_SMSCODE="mobile:smscode";

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
}
