package com.tyut.notice.service;

import com.tyut.core.response.ServerResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * Created by Fant.J.
 * 2018/4/30 13:31
 */
public interface NoticeService {
    /** 查询单个*/
    ServerResponse selectById(Integer id);
    /** 查询全部*/
    ServerResponse selectAll(Integer page,Integer size);
}
