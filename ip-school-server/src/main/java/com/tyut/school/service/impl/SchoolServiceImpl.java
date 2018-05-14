package com.tyut.school.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.School;
import com.tyut.core.response.ServerResponse;
import com.tyut.school.repository.SchoolRepository;
import com.tyut.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/4/24 18:50
 */
@Service(version = "2.0.1")
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    /**
     * 增加
     *
     * @param user
     */
    @Override
    public ServerResponse insert(School user) {
        return null;
    }

    /**
     * 更新
     *
     * @param user
     */
    @Override
    public ServerResponse update(School user) {
        return null;
    }

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public ServerResponse selectById(int id) {
        return ServerResponse.createBySuccess(schoolRepository.findOne(1));
    }

    /**
     * 查询全部
     */
    @Override
    public ServerResponse selectAll() {
        return ServerResponse.createBySuccess(schoolRepository.findAll());
    }

    /**
     * 模糊查询
     */
    @Override
    public ServerResponse selectLike(String likeName) {
        List<School> schoolBySchoolNameLike = schoolRepository.getSchoolBySchoolNameLike(likeName);
        return ServerResponse.createBySuccess(schoolBySchoolNameLike);
    }
}
