package com.tyut.user.repostory;

import com.tyut.core.pojo.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/5/31 10:51
 */
//@Repository
public interface GroupMemJpa extends JpaRepository<GroupMembers,Integer> {
}
