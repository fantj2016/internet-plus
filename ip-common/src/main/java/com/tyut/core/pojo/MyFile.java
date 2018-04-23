package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "ip_file")
public class MyFile  implements Serializable {

    @Id
    @GeneratedValue
    private Integer fileId;

    private String fileName;

    private Integer fileType;

    private String fileUrl;

    private Integer userId;

    private Integer cptId;

}