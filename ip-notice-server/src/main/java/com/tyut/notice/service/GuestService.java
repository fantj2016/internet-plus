package com.tyut.notice.service;

import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/4/30 15:38
 */
public interface GuestService{
    /** 查询单个*/
    ServerResponse selectById(Integer id);
    /** 根据比赛id查询全部专家*/
    ServerResponse selectAll0(Integer id);
    /** 根据比赛id查询全部评委*/
    ServerResponse selectAll1(Integer id);
}
