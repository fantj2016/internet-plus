package com.tyut.group.repostroy;

import com.tyut.core.pojo.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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




    /** 根据 key 查询队伍简介信息 */
    @Query(value = "select g.group_id,g.group_name from ip_group g WHERE g.group_key=?1",nativeQuery = true)
    List<Object> selectByKey(@Param("key") String key);

    /** 根据 key 查询队伍详细信息 */
    @Query(value = "select new com.tyut.core.pojo.Group(groupName, groupPhone, groupType, groupAddress) from ip_group g where g.group_key=?1")
    Group queryGroupInfoByKey(@Param("key") String key);

    @Modifying
    @Query("update ")
    int updateGroupInfo(Group group);


}
