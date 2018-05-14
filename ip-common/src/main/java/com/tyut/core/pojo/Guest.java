package com.tyut.core.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ip_guest")
public class Guest  implements Serializable {

    @Id
    @GeneratedValue
    private Integer guestId;

    private Integer guestCptId;

    private String guestName;

    private Integer guestType;

    private String guestImg;

    private String guestDescrip;

}