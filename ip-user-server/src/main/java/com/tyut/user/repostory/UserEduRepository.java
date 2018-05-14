package com.tyut.user.repostory;

import com.tyut.core.pojo.UserEdu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Fant.J.
 * 2018/5/6 15:59
 */
public interface UserEduRepository extends JpaRepository<UserEdu,Integer> {
}
