package com.tyut.user.dao;

import com.tyut.core.pojo.GroupMembers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    int updateAgreeStatus(@Param("groupId") Integer groupId,@Param("userId") String userId);
    /** 拒绝用户加入（用户状态修改成2）*/
    int updateRejectStatus(@Param("groupId") Integer groupId,@Param("userId") String userId);
    /** 查询用户是否是队长 */
    int selectIndentity(@Param("userId") String userId,@Param("groupId") Integer groupId);
    /** 删除某个队员 */
    int deleteSomeone(@Param("groupId") Integer groupId,@Param("userId") String userId);
    /** 查询是否存在某个用户 */
    int isExsitSomeone(@Param("groupId") Integer groupId,@Param("userId") String userId);
    /** 根据groupId查询队长的UserId */
    String getHeaderUserId(@Param("groupId") Integer groupId);
    /** 根据 用户id 查询 详细信息 */
    List<GroupMembers> queryInfoByUserId(String userId);
    int isJoinCpt(@Param("userId") String userId,@Param("cptId") Integer cptId);
}