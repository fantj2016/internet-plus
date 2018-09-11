package com.tyut.notice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.Competition;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.repostory.CompetitionRepostory;
import com.tyut.notice.service.CompetitionService;
import com.tyut.notice.vo.CompetitionTitleListVo;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fant.J.
 * 2018/5/6 16:05
 */
@Service(version = "2.0.0")
@CacheConfig(cacheNames = "cpt")
public class CompetitionServiceImpl  implements CompetitionService {



    @Autowired
    private CompetitionRepostory repostory;
    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    @Cacheable(key = "'selectById'+#id")
    public ServerResponse selectById(Integer id) {
        Competition one = repostory.findOne(id);
        if (one != null){
            return ServerResponse.createBySuccess(one);
        }
        return null;
    }

    /**
     * 查询题目列表
     */
    @Override
    public ServerResponse selectTitleList() {
        List<CompetitionTitleListVo> competitions = repostory.selectTitleList();
        if (StringUtils.isEmpty(competitions)){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(competitions);
    }
}
