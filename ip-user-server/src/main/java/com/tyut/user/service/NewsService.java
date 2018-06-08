package com.tyut.user.service;

import com.tyut.core.pojo.News;
import com.tyut.core.response.ServerResponse;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/6/1 23:33
 */
public interface NewsService {
    /** 查询消息列表 */
    ServerResponse selectNewsList(String userId,Integer pageNum,Integer pageSize);
    /** 消息状态修改（已读）*/
    ServerResponse hasRead(Integer newsId);

    /** 增加消息 */
    ServerResponse addNews(String userId,String content);
}
