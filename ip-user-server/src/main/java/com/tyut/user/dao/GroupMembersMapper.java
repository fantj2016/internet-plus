package com.tyut.user.dao;

import com.tyut.core.pojo.GroupMembers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GroupMembersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupMembers record);

    int insertSelective(GroupMembers record);

    GroupMembers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMembers record);

    int updateByPrimaryKey(GroupMembers record);
    /** 同意用户加入（用户状态修改成1）*/
    int updateStatus(@Param("groupId") Integer groupId,@Param("userId") String userId);
    /** 查询用户是否是队长 */
    int selectIndentity(@Param("userId") String userId,@Param("groupId") Integer groupId);
}