package com.tyut.core.constants;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/26 22:59
 */
public enum SexEnum implements Serializable {
    GIRL(0,"女"),
    BOY(1,"男");

    SexEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;
    private String value;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
