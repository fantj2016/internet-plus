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
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
     *
     * @param userId
     */
    @Override
    public ServerResponse selectNewsList(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = newsMapper.selectNewsList(userId);
        PageInfo<News> appsPageInfo = new PageInfo<>(news);

        if (StringUtils.isEmpty(news)){
            return ServerResponse.createByErrorMessage("查询消息列表失败");
        }
        log.info("查询到的消息数目{}",appsPageInfo.getList());
        appsPageInfo.getList().forEach(p-> System.out.println(p.toString()));
        return ServerResponse.createBySuccess(appsPageInfo.getList());
    }

    /**
     * 消息状态修改（已读）
     *
     * @param newsId
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
     * 增加消息
     *
     * @param userId
     * @param content
     */
    @Override
    public ServerResponse addNews(Integer userId, String content) {
        News news = new News();
        news.setUserId(userId);
        news.setNewsContent(content);
        news.setNewsCreateTime(new Date());
        news.setNewsStatus(0);
        int insert = newsMapper.insert(news);
        if (insert!=1){
            return ServerResponse.createByErrorMessage("添加消息失败");
        }
        return ServerResponse.createBySuccessMessage("添加消息成功");
    }
}
