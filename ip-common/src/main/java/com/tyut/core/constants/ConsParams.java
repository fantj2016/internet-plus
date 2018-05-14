package com.tyut.core.constants;

/**
 * Created by Fant.J.
 * 2018/5/3 11:57
 */
public class ConsParams {
    public interface Auth{
        String GET_CLIENT_ID = "internet_plus";
        String GET_SECRET = "internet_plus";
        String GET_SIGNING_KEY = "internet_plus";
        Integer GET_TOKEN_VALIDITY_SECONDS = 72000;
        String [] GE_TAUTHORIZED_GRANT_TYPES = {"refresh_token","password"};
        String GE_TSCOPES = "all";
    }
    public interface Email{
        /**
         * 是否存在
         */
        String GET_EXIST_SUCCESS_MSG = "OK";
        String GET_EXIST_ERROR_MSG = "该邮箱已被注册";
        /**
         * 格式是否正确
         */
        String GET_FORMAT_ERROR_MSG = "邮箱格式不正确";
        String GET_FORMAT_SUCCESS_MSG = "OK";
    }
    public interface Phone{
        /**
         * 是否存在
         */
        String GET_EXIST_SUCCESS_MSG = "OK";
        String GET_EXIST_ERROR_MSG = "该手机号已被注册";
        /**
         * 格式是否正确
         */
        String GET_FORMAT_ERROR_MSG = "手机号格式不正确";
        String GET_FORMAT_SUCCESS_MSG = "OK";
    }
    public interface Login{
        /**
         * 两次密码不一致
         */
        String PASSWD_NOT_EQUIST_MSG = "两次密码输入不一致";
    }
}
