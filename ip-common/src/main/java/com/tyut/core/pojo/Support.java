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
@Table(name = "ip_cpt_support")
public class Support  implements Serializable {
    @Id
    @GeneratedValue
    private Integer supId;

    private String supName;

    private Date supCreateTime;

    private Date supUpdateTime;

}