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
@Table(name = "ip_user_edu")
public class UserEdu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eduId;
    private String eduName;
}
