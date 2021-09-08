package com.lazydog.imeadia.mapper;

import com.lazydog.imedia.api.my.mapper.MyMapper;
import com.lazydog.pojo.AppUser;

import java.util.List;

public interface AppUserMapper extends MyMapper<AppUser> {
    List<AppUser> findAnything();
}