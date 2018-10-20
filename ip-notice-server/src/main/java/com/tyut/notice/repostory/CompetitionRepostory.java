package com.tyut.notice.repostory;

import com.tyut.core.pojo.Competition;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.vo.CompetitionTitleListVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/5/6 13:50
 */
@Repository
public interface CompetitionRepostory extends JpaRepository<Competition,Integer> {


    /** 查询 赛题列表 */
    @Query("select new com.tyut.notice.vo.CompetitionTitleListVo(cptId,cptType,cptName,cptIntro,cptImg,cptStatus) from Competition c where c.cptStatus != 5")
    List<CompetitionTitleListVo> selectTitleList();

}
