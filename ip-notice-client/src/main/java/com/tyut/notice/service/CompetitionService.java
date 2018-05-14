package com.tyut.notice.service;

import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/5/6 13:51
 */
public interface CompetitionService {
    /** 查询单个*/
    ServerResponse selectById(Integer id);
}
