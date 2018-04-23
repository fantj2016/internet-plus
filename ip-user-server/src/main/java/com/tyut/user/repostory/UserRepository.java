package com.tyut.user.repostory;

import com.tyut.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/4/21 13:39
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
