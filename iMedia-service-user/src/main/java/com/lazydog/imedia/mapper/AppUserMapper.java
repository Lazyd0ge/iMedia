package com.lazydog.imedia.mapper;

import com.lazydog.imedia.api.my.mapper.MyMapper;
import com.lazydog.pojo.AppUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppUserMapper extends MyMapper<AppUser> {
    List<AppUser> findAnything();
}