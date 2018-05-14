package com.tyut.user.repostory;

import com.tyut.core.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/4/21 13:39
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 校验登录密码
     */
    @Query(value = "select u.userPasswd from User u where u.userEmail=:email")
    String selectPasswdByEmail(@Param("email") String email);
    @Query(value = "select u.userPasswd from User u where u.userPhone=:phone")
    String selectPasswdByPhone(@Param("phone") String phone);

    /**
     * 校验是否存在
     */

    @Query(value = "select count(u.userId) from User u where u.userPhone=:phone")
    int  selectIsExistPhone(@Param("phone") String phone);
    @Query(value = "select count(u.userId) from User u where u.userEmail=:email")
    int  selectIsExistEmail(@Param("email") String email);

    /***
     * (用户登录返回使用)
     * 根据id查询 用户 名字+id+电话+学校
     */
    @Override
    @Query(value = "select u.userId,u.userPhone,u.userSchoolId,u.userName from User u where  u.userId =:id")
    User findOne(@Param("id")Integer id);



}
