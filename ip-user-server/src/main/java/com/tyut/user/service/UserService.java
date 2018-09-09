package com.tyut.user.service;


import com.tyut.core.pojo.User;
import com.tyut.core.pojo.UserFile;
import com.tyut.core.response.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/4/21 13:40
 */
public interface UserService extends Serializable{
    /** 增加*/
    ServerResponse insert(User user);
    /** 更新*/
    ServerResponse update(User user);
    /** 查询单个*/
    ServerResponse selectById(String id);
    /** 根据email查询密码 */
    String selectPasswdByEmail(String email);
    /** 根据 phone 查询密码 */
    String selectPasswdByPhone(String phone);
    /** 判断 email 是否存在*/
    ServerResponse isExistEmail(String email);
    /** 判断 phone 是否存在*/
    ServerResponse isExistPhone(String phone);
    /** 通过 手机或者 邮箱 查询 个人信息 */
    ServerResponse selectMe(String str);
    /** 上传头像 */
    ServerResponse uploadPortrait(String username,String imgStr,String path) throws IOException;
    /** 上传证明（学生证）*/
    ServerResponse uploadCert(String username,String imgStr,String path) throws IOException;
    /** 上传作品 */
    ServerResponse uploadWork(UserFile userFile) throws IOException;
    /** 1.找回密码-发送邮件 */
    ServerResponse findPasswd(String email) throws InterruptedException;
    /** 2.找回密码-有效校验 */
    ServerResponse isValid(String valid);
    /** 3.找回密码-修改密码 */
    ServerResponse updatePasswd(String passwd,String valid);
}
