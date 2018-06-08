package com.tyut.user.myEmail.emailEnum;

import java.io.Serializable;

/**
 * 自定义的枚举类型，枚举类型包含了邮件内容的类型
 * Created by Fant.J.
 */
public enum MailContentTypeEnum  implements Serializable {
    HTML("text/html;charset=UTF-8"), //html格式
    TEXT("text")
    ;
    private String value;

    MailContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
