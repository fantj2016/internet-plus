package com.tyut.notice.repostory;

import com.tyut.core.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/4/30 13:30
 */
@Repository
public interface NoticeRepostory extends JpaRepository<Notice,Integer> {

}
