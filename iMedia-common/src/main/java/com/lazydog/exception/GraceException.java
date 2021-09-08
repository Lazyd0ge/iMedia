package com.lazydog.exception;

import com.lazydog.result.ResponseStatusEnum;

public class GraceException {
    public static void display(ResponseStatusEnum responseStatusEnum){
        throw new MyCustomException(responseStatusEnum);
    }
}
