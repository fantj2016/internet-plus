package com.tyut.school.service;

import com.tyut.core.pojo.School;
import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/4/24 18:49
 */
public interface SchoolService {
    /** 增加*/
    ServerResponse insert(School user);
    /** 更新*/
    ServerResponse update(School user);
    /** 查询单个*/
    ServerResponse selectById(int id);
    /** 查询全部 */
    ServerResponse selectAll();
    /** 模糊查询 */
    ServerResponse selectLike(String like);
}
