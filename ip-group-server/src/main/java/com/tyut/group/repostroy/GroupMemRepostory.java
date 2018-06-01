package com.tyut.group.repostroy;

import com.tyut.core.pojo.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/5/26 23:27
 */
@Repository
public interface GroupMemRepostory extends JpaRepository<GroupMembers,Integer> {

    /** 查询队伍列表 */
    @Query(nativeQuery = true,value = "select u.user_name,u.user_phone,m.group_name,m.group_id,m.user_identity,m.id " +
            "from ip_user as u,ip_group_members m where u.user_id=m.user_id and u.user_id = ?1\n" +
            "GROUP BY m.group_id ORDER BY m.group_id DESC")
    List<Object> findAllByUserId(@Param("userId") Integer userId);

    /** 根据队伍id 查询队伍详情*/
    @Query(value = "select g.group_name,u.user_name,u.user_phone,g.user_identity,g.user_status\n" +
            "from ip_group_members as g,ip_user as u where g.user_id=u.user_id and g.group_id=?1\n" +
            "ORDER BY g.user_identity desc,g.user_status desc",
            nativeQuery = true)
    List<Object> findAllByGroupId(@Param("groupId") Integer groupId);

    /** 校验是否是队长身份 */
    @Query(value = "select m.user_identity from ip_group_members as m where m.user_id=?1 and m.group_id=?2",nativeQuery = true)
    int findByUserId(@Param("userId")Integer userId,@Param("groupId")Integer groupId);

    /** 修改用户为已加入 */
    @Modifying
    @Query(value = "UPDATE ip_group_members m set m.user_identity = 1 where m.group_id=?1  and m.user_id=?2",nativeQuery = true)
    int updateUserIdentity(@Param("groupId") Integer groupId,@Param("userId")Integer userId);
}
