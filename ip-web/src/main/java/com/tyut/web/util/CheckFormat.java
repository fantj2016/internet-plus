package com.tyut.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fant.J.
 * 2018/5/3 16:23
 * 130、131、132、133、134(0-8)、135、136、137、138、139、
 * 145、147、、149
 * 150、151、152、153、 155、156、157、158、159、
 * 166、
 * 171、173、175、176、177、178、
 * 180、181、 182、183、184、187、188、185、186、189
 * 199、198
 */
public class CheckFormat {
    public static boolean isEmail(String email){
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhone(String phone){
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
}
