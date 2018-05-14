package com.tyut.notice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.Competition;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.repostory.CompetitionRepostory;
import com.tyut.notice.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Fant.J.
 * 2018/5/6 16:05
 */
@Service(version = "2.0.0")
public class CompetitionServiceImpl  implements CompetitionService {



    @Autowired
    private CompetitionRepostory repostory;
    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public ServerResponse selectById(Integer id) {
        Competition one = repostory.findOne(id);
        if (one != null){
            return ServerResponse.createBySuccess(one);
        }
        return null;
    }
}
