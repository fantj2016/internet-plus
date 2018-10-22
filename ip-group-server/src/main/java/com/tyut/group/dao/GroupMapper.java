package com.tyut.group.dao;

import com.tyut.core.pojo.Group;
import com.tyut.group.vo.GroupVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface GroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(Group record);

    /**
     * 根据 key 更新 队伍信息
     * @param record
     * @return
     */
    int updateByGroupKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    /** 根据 key 查询队伍 */
    List<GroupVo> selectByKey(@Param("key") String key);
}