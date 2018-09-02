package com.tyut.core.constants;

import com.tyut.core.utils.FTPUtil;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/3 11:57
 */
public class ConsParams implements Serializable {

    public interface Portrait{
        String BOY_PORTRAIT = "/portrait/boy.png";
        String GIRL_PROTRAIT = "/portrait/girl.png";
        String PRIFIX_PORTRAIT = "http://"+FTPUtil.getFtpIp();
    }
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

    /**
     * 性别ENUM
     */
    public enum SexEnum{
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
    /**
     * 上传文件类型：0是证明，1是作品
     */
    public interface FileType{
        int USER_FILE_OF_WORK = 1;
        int USER_FILE_OF_CERTIFY = 0;
    }
    /**
     * 文件后缀
     */
    public interface FilePostfix{
        String IMG_POSTFIX = "jpg";
        String ZIP_POSTFIX = "zip";
    }
    /**\
     * Ftp 文件上传/获取  路径
     */
    public interface FtpFilePath{
        String FTP_IMG_PATH = "/image/";
        String FTP_ZIP_PATH = "/works/";
    }
}
