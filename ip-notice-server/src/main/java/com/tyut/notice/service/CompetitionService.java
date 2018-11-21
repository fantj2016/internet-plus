package com.tyut.notice.service;

import com.tyut.core.pojo.Competition;
import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/5/6 13:51
 */
public interface CompetitionService {
    /** 查询单个*/
    Competition selectById(Integer id);
    /** 查询题目列表 */
    ServerResponse selectTitleList();
}
