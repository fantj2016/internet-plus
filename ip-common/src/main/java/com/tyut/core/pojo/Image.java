package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "ip_image")
public class Image  implements Serializable {

    @Id
    @GeneratedValue
    private Integer imgId;

    private Integer imgType;

    private String imgName;

    private String imgUrl;
}