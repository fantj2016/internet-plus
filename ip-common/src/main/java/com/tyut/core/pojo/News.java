package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fant.J.
 * 2018/6/1 23:35
 */
@Data
@Entity
@Table(name = "ip_news")
public class News implements Serializable {

    @Id
    @GeneratedValue
    private Integer newsId;

    private Integer userId;

    private Integer newsStatus;

    private Date newsCreateTime;

    private String newsContent;

}
