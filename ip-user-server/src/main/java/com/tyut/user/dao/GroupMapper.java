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

    int updateByPrimaryKey(Group record);

    /** 根据 key 查询队伍 */
    List<GroupVo> selectByKey(@Param("key") String key);
    /** 通过key查询队伍详情 */
    Group queryInfo(String key);
    /** 通过key 修改队伍详情 */
    int updateInfoSelective( Group group);
}