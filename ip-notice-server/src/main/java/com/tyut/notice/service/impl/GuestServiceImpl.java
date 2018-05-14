package com.tyut.notice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.Guest;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.repostory.GuestRepostory;
import com.tyut.notice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * Created by Fant.J.
 * 2018/4/30 15:38
 */
@Service(version = "2.0.0")
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestRepostory guestRepostory;
    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public ServerResponse selectById(Integer id) {
        return null;
    }

    /**
     * 根据比赛id查询全部专家
     *
     * @param id
     */
    @Override
    public ServerResponse selectAll0(Integer id) {
        if (id == null){
            return ServerResponse.createByErrorMessage("比赛id不能为空");
        }
        List<Guest> all0 = guestRepostory.getAll0(id);
        if (CollectionUtils.isEmpty(all0)){
            return ServerResponse.createByErrorMessage("竞赛不存在或后台没有添加评委");
        }
        return ServerResponse.createBySuccess(all0);
    }

    /**
     * 根据比赛id查询全部评委
     *
     * @param id
     */
    @Override
    public ServerResponse selectAll1(Integer id) {
        if (id == null){
            return ServerResponse.createByErrorMessage("比赛id不能为空");
        }
        List<Guest> all1 = guestRepostory.getAll1(id);
        if (CollectionUtils.isEmpty(all1)){
            return ServerResponse.createByErrorMessage("竞赛不存在或后台没有添加评委");
        }
        return ServerResponse.createBySuccess(all1);
    }
}
