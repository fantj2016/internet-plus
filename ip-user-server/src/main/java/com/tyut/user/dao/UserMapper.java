package com.tyut.user.dao;

import com.tyut.core.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /** 更改头像 */
    int updatePortraitByEmail(User record);
    int updatePortraitByPhone(User record);
    /** 通过email 修改密码 */
    int updatePasswdByEmail(User user);

    int updateStatusByEmail(String username);

    int updateStatusByPhone(String username);
}