package com.tyut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyut.core.pojo.News;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.dao.NewsMapper;
import com.tyut.user.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fant.J.
 * 2018/6/1 23:39
 */
@Service(version = "2.0.0")
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    /**
     * 查询消息列表
     */
    @Override
    public ServerResponse selectNewsList(String userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectNewsList(userId);
        PageInfo<News> appsPageInfo = new PageInfo<>(news);

        if (StringUtils.isEmpty(news)){
            return ServerResponse.createByErrorMessage("查询消息列表失败");
        }
        log.info("查询到的消息appsPageInfo{}",appsPageInfo);
        return ServerResponse.createBySuccess(appsPageInfo);
    }

    /**
     * 消息状态修改（已读）
     */
    @Override
    public ServerResponse hasRead(Integer newsId) {
        int i = newsMapper.hasRead(newsId);
        if (i!=0){
            return ServerResponse.createBySuccessMessage("消息已读");
        }
        return ServerResponse.createByErrorMessage("修改消息状态出错");
    }

    /**
     * 消息状态修改（忽略）
     *
     * @param newsId
     */
    @Override
    public ServerResponse hasIgnore(Integer newsId) {
        int i = newsMapper.hasIgnore(newsId);
        if (i!=0){
            return ServerResponse.createBySuccessMessage("消息已忽略");
        }
        return ServerResponse.createByErrorMessage("修改消息状态出错");
    }

    /**
     * 增加消息
     *
     * @param userId
     * @param content
     */
    @Override
    public ServerResponse addNews(String userId, String content) {
        News news = new News();
        news.setUserId(userId);
        news.setNewsContent(content);
        news.setNewsCreateTime(new Date());
        news.setNewsIgnore(0);
        news.setNewsStatus(0);
        int insert = newsMapper.insert(news);
        if (insert!=1){
            return ServerResponse.createByErrorMessage("添加消息失败");
        }
        return ServerResponse.createBySuccessMessage("添加消息成功");
    }

    /**
     * 查询未读消息个数
     */
    @Override
    public ServerResponse selectCountNotRead(String userId) {
        //在这里可以加一个防止横向越权的操作（防止授权用户互相查看信息）
        int i = newsMapper.selectCountNotRead(userId);
        return ServerResponse.createBySuccess(i);
    }

    @Override
    @Transactional
    public ServerResponse onekeyIgnore(String userId) {
        List<News> newsList = newsMapper.listNotRead(userId);
        List<Integer> list = newsList.stream().map(News::getNewsId).collect(Collectors.toList());
        log.info("list"+list);
        int i = newsMapper.onekeyIgnore(list);
        if (i == 0){
            return ServerResponse.createByErrorMessage("一键已读失败");
        }
        return ServerResponse.createBySuccessMessage("一键已读成功");
    }
}
