package com.tyut.group.dao;

import com.tyut.core.pojo.GroupMembers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMembersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupMembers record);

    int insertSelective(GroupMembers record);

    GroupMembers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMembers record);

    int updateByPrimaryKey(GroupMembers record);

    int updateStatus(Integer groupId,Integer userId);
}