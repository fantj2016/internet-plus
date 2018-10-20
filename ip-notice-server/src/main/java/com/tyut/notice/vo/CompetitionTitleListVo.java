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

    private String cptType;

    private String cptName;

    private String cptIntro;

    private String cptImg;

    private String cptIcon;

    private Integer cptStatus;

    public CompetitionTitleListVo(Integer cptId, String cptType, String cptName, String cptIntro, String cptImg, String cptIcon, Integer cptStatus) {
        this.cptId = cptId;
        this.cptType = cptType;
        this.cptName = cptName;
        this.cptIntro = cptIntro;
        this.cptImg = cptImg;
        this.cptIcon = cptIcon;
        this.cptStatus = cptStatus;
    }
}
