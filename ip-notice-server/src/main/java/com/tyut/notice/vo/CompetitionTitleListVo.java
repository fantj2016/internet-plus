package com.tyut.notice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 竞赛题目列表
 * Created by Fant.J.
 * 2018/6/1 22:03
 */
@Data
public class CompetitionTitleListVo implements Serializable {

    private Integer cptId;

    private String cptName;

    private String cptIntro;

    public CompetitionTitleListVo() {
    }

    public CompetitionTitleListVo(Integer cptId, String cptName, String cptIntro) {
        this.cptId = cptId;
        this.cptName = cptName;
        this.cptIntro = cptIntro;
    }
}
