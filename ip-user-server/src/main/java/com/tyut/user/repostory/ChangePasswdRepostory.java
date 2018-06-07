package com.tyut.user.repostory;

import com.tyut.core.pojo.ChangePasswd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/6/6 21:30
 */
@Repository
public interface ChangePasswdRepostory extends JpaRepository<ChangePasswd,Integer> {

    /** 查询最新的一条记录 */
    @Query("select c from ChangePasswd c where c.userKey=?1 order by c.createTime desc ")
    ChangePasswd findLatest(String valid);
}
