package com.tyut.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/26 23:25
 */
@Data
public class JoinVo implements Serializable {
    private String key;
    private Integer userId;
}
