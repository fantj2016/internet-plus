package com.tyut.user.dao;

import com.tyut.core.pojo.Group;
import com.tyut.user.vo.GroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
@Mapper
public interface GroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    /** 根据 key 查询队伍 */
    List<GroupVo> selectByKey(@Param("key") String key);
}