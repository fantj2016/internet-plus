package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/6 15:57
 */
@Data
@Entity
@Table(name = "ip_user_file")
public class UserFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fileId;
    private String fileName;
    private Integer fileType;
    private String fileUrl;
    private String username;
    private Integer cptId;
    private Integer fileStatus;
}
