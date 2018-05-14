package com.tyut.notice.repostory;

import com.tyut.core.pojo.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/4/30 15:44
 */
@Repository
public interface GuestRepostory extends JpaRepository<Guest,Integer> {

    @Query("from Guest where guestCptId=:id and guestType=0")
    List<Guest> getAll0(@Param("id") Integer id);
    @Query("from Guest where guestCptId=:id and guestType=1")
    List<Guest> getAll1(@Param("id")Integer id);
}
