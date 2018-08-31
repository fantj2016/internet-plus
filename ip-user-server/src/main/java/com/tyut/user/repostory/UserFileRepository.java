package com.tyut.user.repostory;

import com.tyut.core.pojo.UserEdu;
import com.tyut.core.pojo.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Fant.J.
 * 2018/5/6 15:59
 */
public interface UserFileRepository extends JpaRepository<UserFile,Integer> {
}
