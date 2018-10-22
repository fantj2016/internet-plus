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
public interface UserRepository extends JpaRepository<User, String> {

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
    User findOne(@Param("id")String id);

    /**
     * 根据 查询用户信息
     */
//    @Query(value = "select new com.tyut.user.dto.UserDto(userId,userPhone,userSchool,userEmail,userName,userAcademy,userEducation,userGrade,userProfession,userSex,userStuNum,userPortrait)  from User u where u.userPhone=:phone group by u.userName")
//    UserDto  selectByPhone(@Param("phone") String phone);
//    @Query(value = "select new com.tyut.user.dto.UserDto(userId,userPhone,userSchool,userEmail,userName,userAcademy,userEducation,userGrade,userProfession,userSex,userStuNum,userPortrait)  from User u where u.userEmail=:email")
//    UserDto selectByEmail(@Param("email") String email);

//    @Query(nativeQuery = true,value = "select u.user_id,u.user_phone,s.school_name,u.user_email,u.user_name,u.user_academy,u.user_education,u.user_grade,u.user_profession,u.user_sex,u.user_stu_num,u.user_portrait  from ip_user as u,ip_school as s where u.user_school_id=s.id and u.user_phone=?1")
//    Object  selectByPhone(@Param("phone") String phone);
//    @Query(nativeQuery = true,value = "select u.user_id,u.user_phone,s.school_name,u.user_email,u.user_name,u.user_academy,u.user_education,u.user_grade,u.user_profession,u.user_sex,u.user_stu_num,u.user_portrait  from ip_user as u,ip_school as s where u.user_school_id=s.id and u.user_email=?1")
//    Object  selectByEmail(@Param("email") String email);

}
