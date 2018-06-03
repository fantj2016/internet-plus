package com.tyut.notice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.Notice;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.repostory.NoticeRepostory;
import com.tyut.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Fant.J.
 * 2018/4/30 13:43
 */
@Service(version = "2.0.0")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepostory noticeRepostory;

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public ServerResponse selectById(Integer id) {
        if (id == null){
            return ServerResponse.createBySuccessMessage("请传入正确的参数id");
        }
        return ServerResponse.createBySuccess(noticeRepostory.findOne(id));
    }

    /**
     * 查询全部
     */
    @Override
    public ServerResponse selectAll(Integer page,Integer size) {
        Pageable pageable = new PageRequest(page,size,Sort.Direction.DESC,"noticeId");
        Iterator<Notice> all = noticeRepostory.findAll(pageable).iterator();
        List<Notice> list = new ArrayList<Notice>();
        while (all.hasNext()){
           list.add(all.next());
        }
        if (all == null){
            return ServerResponse.createByErrorMessage("查询公告列表失败");
        }
        return ServerResponse.createBySuccess(list);
    }

}
