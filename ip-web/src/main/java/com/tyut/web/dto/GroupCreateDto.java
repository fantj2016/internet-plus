package com.tyut.web.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/27 16:03
 */
@Data
public class GroupCreateDto  implements Serializable {
    private String groupName;

    private String groupHeaderId;

    private String groupPhone;

    private Integer groupType;

    private String groupAddress;
}
