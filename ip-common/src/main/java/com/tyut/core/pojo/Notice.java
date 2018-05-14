package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "ip_notice")
public class Notice  implements Serializable {

    @Id
    @GeneratedValue
    private Integer noticeId;

    private String noticeTitle;

    private String noticeImg;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private String noticeContent;

}