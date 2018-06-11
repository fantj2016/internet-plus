package com.tyut.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * Created by Fant.J.
 * 2018/5/9 10:32
 */

@Data
public class UserVo implements Serializable {

    private String userId;

    private String userName;

    private Integer userSchoolId;

    private String userPhone;

}
