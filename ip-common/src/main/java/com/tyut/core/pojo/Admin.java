package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "ip_admin")
public class Admin  implements Serializable {

    @Id
    @GeneratedValue
    private Integer adminId;

    private String adminName;

    private String adminLoginName;

    private String adminLoginPwd;

    private Integer adminLevel;

}