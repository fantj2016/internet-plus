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
 * 2018/6/6 21:22
 */

@Data
@Entity
@Table(name = "ip_change_passwd")
public class ChangePasswd implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String userKey;

    private Date createTime;
}
