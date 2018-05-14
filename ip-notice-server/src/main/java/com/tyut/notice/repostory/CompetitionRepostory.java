package com.tyut.notice.repostory;

import com.tyut.core.pojo.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fant.J.
 * 2018/5/6 13:50
 */
@Repository
public interface CompetitionRepostory extends JpaRepository<Competition,Integer> {
}
