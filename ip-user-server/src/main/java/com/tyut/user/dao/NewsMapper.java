package com.tyut.user.dao;

import com.tyut.core.pojo.News;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer newsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    /** 查询用户消息列表 */
    List<News> selectNewsList(String userId);

    /** 修改为已读 */
    int hasRead(Integer newsId);
    /** 修改为忽略 */
    int hasIgnore(Integer newsId);
    /** 查询未读数量 */
    int selectCountNotRead(String userId);

}