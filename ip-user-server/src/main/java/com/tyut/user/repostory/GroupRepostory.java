package com.tyut.user.repostory;

import com.tyut.core.pojo.Group;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/5/21 20:43
 */
@Repository

public interface GroupRepostory extends JpaRepository<Group,Integer> {



//
//    /** 根据 key 查询队伍 */
//    @Query(value = "select g.group_id,g.group_name from ip_group g WHERE g.group_key=?1",nativeQuery = true)
//    List<Object> selectByKey(String key);
    /** 根据 key 查询队伍 */
    @Query("select g.groupId from Group g where g.groupName=?1")
    List<Group> isExsitGroupName(String groupName);
    /**
     * 根据userid 获取所属组的 集合
     */
//    @Query("select u.user_name from ip_user u,ip_group g where g.group_id=?1 and g.group_header_id=u.user_id")
//    String queryHeaderByGroupId(Integer groupId);
}
