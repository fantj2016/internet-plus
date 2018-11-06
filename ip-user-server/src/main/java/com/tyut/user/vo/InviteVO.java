package com.tyut.user.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InviteVO implements Serializable {

    private String cptName;

    private String headName;

    private String groupName;

    private String groupPhone;

    private Integer groupId;


}
