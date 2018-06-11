package com.tyut.user.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fant.J.
 * 2018/5/3 16:23
 */
public class CheckFormat implements Serializable {
    public static boolean isEmail(String email){
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhone(String phone){
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0,5-9])|(19[89]))\\d{8})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
}
