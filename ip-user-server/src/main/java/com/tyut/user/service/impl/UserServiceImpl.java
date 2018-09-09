package com.tyut.user.service.impl;

import com.google.common.collect.Lists;
import com.tyut.core.constants.ConsParams;
import com.tyut.core.pojo.ChangePasswd;
import com.tyut.core.pojo.User;
import com.tyut.core.pojo.UserFile;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.utils.Base64ToFile;
import com.tyut.core.utils.CheckFormat;
import com.tyut.core.utils.FTPUtil;
import com.tyut.core.vo.UserVo;
import com.tyut.user.dao.UserMapper;
import com.tyut.core.vo.GetMeVo;
import com.tyut.user.myEmail.MailSender;
import com.tyut.user.myEmail.emailEnum.MailContentTypeEnum;
import com.tyut.user.repostory.ChangePasswdRepostory;
import com.tyut.user.repostory.UserFileRepository;
import com.tyut.user.repostory.UserRepository;
import com.tyut.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Fant.J.
 * 2018/4/21 13:41
 */
@Service(version = "2.0.0")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ChangePasswdRepostory changePasswdRepostory;
    @Autowired
    private UserFileRepository fileRepository;

    /**
     * 增加
     */
    @Override
    public ServerResponse insert(User user) {
        User save = userRepository.save(user);
        if (save != null){
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }

    /**
     * 更新
     */
    @Override
    public ServerResponse update(User user) {
        user.setUserUpdateTime(new Date());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i ==0 ){
            return ServerResponse.createByErrorMessage("修改失败");
        }
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 查询单个
     */
    @Override
    public ServerResponse<UserVo> selectById(String id) {
        User user = userRepository.findOne(id);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到该用户");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return ServerResponse.createBySuccess(userVo);
    }

    /**
     * 根据email查询密码
     */
    @Override
    public String selectPasswdByEmail(String email) {
        return userRepository.selectPasswdByEmail(email);
    }

    /**
     * 根据 phone 查询密码
     */
    @Override
    public String selectPasswdByPhone(String phone) {
        return userRepository.selectPasswdByPhone(phone);
    }

    /**
     * 判断 email 是否存在
     */
    @Override
    public ServerResponse isExistEmail(String email) {
        int i = userRepository.selectIsExistEmail(email);
        if (i==0){
            //证明 phone 可以使用
            return ServerResponse.createBySuccess("OK");
        }else {
            return ServerResponse.createByErrorMessage("该邮箱已被注册");
        }
    }

    /**
     * 判断 phone 是否存在
     */
    @Override
    public ServerResponse isExistPhone(String phone) {
        int i = userRepository.selectIsExistPhone(phone);
        if (i==0){
            //证明 phone 可以使用
            return ServerResponse.createBySuccess("OK");
        }else {
            return ServerResponse.createByErrorMessage("该手机号已被注册");
        }
    }

    /**
     * 通过 手机或者 邮箱 查询 个人信息
     */
    @Override
    public ServerResponse selectMe(String str) {
        String common_sql = "select u.user_id,u.user_phone,s.school_name as user_school,u.user_email,u.user_name,u.user_academy,e.edu_name as user_education,u.user_grade,u.user_profession,u.user_sex,u.user_stu_num,u.user_portrait,u.user_education as user_education_id,u.user_school_id,u.user_status  " +
                "from ip_user as u,ip_school as s,ip_user_edu as e  " +
                "where u.user_school_id=s.id and u.user_education=e.edu_id";
        if (CheckFormat.isEmail(str)){
            Query nativeQuery = entityManager.createNativeQuery(common_sql+" and u.user_email=?1");
            Object result = nativeQuery.setParameter(1, str).getSingleResult();
            Object[] o = (Object[]) result;
            return ServerResponse.createBySuccess(converse(o));
        }
        if (CheckFormat.isPhone(str)){
            Query nativeQuery = entityManager.createNativeQuery(common_sql+"  and u.user_phone=?1");
            Object result = nativeQuery.setParameter(1, str).getSingleResult();
            Object[] o = (Object[]) result;
            return ServerResponse.createBySuccess(converse(o));
        }
        log.info(str);
        return ServerResponse.createByErrorMessage("用户名格式不正确");
    }



    /**
     * 上传证明
     */
    @Override
    @Transactional
    public ServerResponse uploadCert(String username, String imgStr,String path) throws IOException {
        String ftpFilePath = "";
        int fileType = ConsParams.FileType.USER_FILE_OF_CERTIFY;
        File newFile = null;
//        if (CheckFormat.isImage(type)){
                String fileName = UUID.randomUUID().toString().replace("-","")+
                        ConsParams.FilePostfix.IMG_POSTFIX;
                newFile = Base64ToFile.base64ToFile(imgStr,path,fileName);
                log.info("file isImage");
                FTPUtil.uploadImage(Lists.newArrayList(newFile));
                ftpFilePath = ConsParams.FtpFilePath.FTP_IMG_PATH;

//            }else if (CheckFormat.isZip(type)){
//                String fileName = UUID.randomUUID().toString().replace("-","")+
//                        ConsParams.FilePostfix.ZIP_POSTFIX;
//                newFile = Base64ToFile.base64ToFile(imgStr,path,fileName);
//                log.info("file isZip");
//                FTPUtil.uploadZip(Lists.newArrayList(newFile));
//                ftpFilePath = ConsParams.FtpFilePath.FTP_ZIP_PATH;
//                fileType = ConsParams.FileType.USER_FILE_OF_WORK;
//            }
        assert newFile != null;
        String name = newFile.getName();
        log.info("newFile.getName::::::::::"+name);
        String fileExtensionName = name.substring(name.lastIndexOf(".")+1);
        log.info("fileExtensionName:::::::::::::"+fileExtensionName);


        boolean delete = newFile.delete();
        if (!delete){ log.info("本地文件删除失败"); }
        UserFile userFile = new UserFile();
        userFile.setFileName(name);
        userFile.setUsername(username);
        userFile.setFileType(fileType);
        userFile.setCptId(0);
        userFile.setFileUrl(ConsParams.Portrait.PRIFIX_PORTRAIT+ftpFilePath+name);
        userFile.setFileStatus(0);
        log.info("文件地址：{}",userFile.getFileUrl());
        UserFile save = fileRepository.save(userFile);
        if (save != null && updateUserStatus(username) == 1){
            return ServerResponse.createBySuccess("上传成功",userFile.getFileUrl());
        }else {
            return ServerResponse.createByErrorMessage("上传作品失败");
        }
    }

    private int updateUserStatus(String username) {
        int result = 0;
        if (CheckFormat.isEmail(username)){
            result = userMapper.updateStatusByEmail(username);
        }else if (CheckFormat.isPhone(username)){
            result = userMapper.updateStatusByPhone(username);
        }
        return result;
    }

    /**
     * 上传作品
     */
    @Override
    public ServerResponse uploadWork(UserFile userFile) throws IOException {
        UserFile save = fileRepository.save(userFile);
        if (save != null){
            return ServerResponse.createBySuccess("上传成功",userFile.getFileUrl());
        }else {
            return ServerResponse.createByErrorMessage("上传作品失败");
        }
    }

    /**
     * 上传头像
     */
    @Override
    public ServerResponse uploadPortrait(String username, String imgStr,String path) throws IOException {
        String fileName = UUID.randomUUID().toString().replace("-","")+
                ConsParams.FilePostfix.IMG_POSTFIX;
        File newFile = Base64ToFile.base64ToFile(imgStr,path,fileName);
        String name = newFile.getName();
        FTPUtil.uploadImage(com.google.common.collect.Lists.newArrayList(newFile));
        boolean delete = newFile.delete();
        if (!delete){ log.info("本地头像删除失败"); }
        User user = new User();
        user.setUserPortrait(ConsParams.Portrait.PRIFIX_PORTRAIT+ConsParams.FtpFilePath.FTP_IMG_PATH+name);
        log.info("修改后的用户头像地址：{}",user.getUserPortrait());
        if (CheckFormat.isPhone(username)){
            user.setUserPhone(username);
            userMapper.updatePortraitByPhone(user);
        }else if (CheckFormat.isEmail(username)){
            user.setUserEmail(username);
            userMapper.updatePortraitByEmail(user);
        }
        return ServerResponse.createBySuccess("修改成功",user.getUserPortrait());
    }

    /**
     * 1.找回密码-发送邮件
     *
     * @param email
     */
    @Override
    @Transactional
    public ServerResponse findPasswd(String email){
        String uuid = UUID.randomUUID().toString().replace("-","");
        try {
            new MailSender()
                    .title("重设你的晋软杯账户密码")
//                    .content("<a href='"+ConsParams.Portrait.PRIFIX_PORTRAIT+"/findPasswd/"+uuid+"' target='_blank'> 点击此链接修改密码</a>")
                    .content("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                            "    <title>Document</title>\n" +
                            "    <style>\n" +
                            "        .box {\n" +
                            "            width: 1140px;\n" +
                            "            height: 680px;\n" +
                            "            position: absolute;\n" +
                            "\n" +
                            "            top: 0;\n" +
                            "            right: 0;\n" +
                            "            bottom: 0;\n" +
                            "            left: 0;\n" +
                            "            margin: auto;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left {\n" +
                            "            width: 790px;\n" +
                            "            height: inherit;\n" +
                            "            position: absolute;\n" +
                            "            left: 0;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border {\n" +
                            "            width: 666px;\n" +
                            "            height: 534px;\n" +
                            "            border: 2px dotted #cacaca;\n" +
                            "            background: #eef0f2;\n" +
                            "            position: absolute;\n" +
                            "            top: 0;\n" +
                            "            right: 0;\n" +
                            "            bottom: 0;\n" +
                            "            left: 0;\n" +
                            "            margin: auto;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .stamp {\n" +
                            "            position: absolute;\n" +
                            "            top: -36px;\n" +
                            "            left: -40px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .logo {\n" +
                            "            position: absolute;\n" +
                            "            right: 20px;\n" +
                            "            top: 24px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border p {\n" +
                            "            position: absolute;\n" +
                            "            font-size: 16px;\n" +
                            "            color: #595757;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .p1 {\n" +
                            "            left: 72px;\n" +
                            "            top: 54px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .p2 {\n" +
                            "            left: 106px;\n" +
                            "            top: 130px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .p3 {\n" +
                            "            right: 136px;\n" +
                            "            top: 222px;\n" +
                            "            color: #f42929;\n" +
                            "            font-size: 14px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .p4 {\n" +
                            "            left: 106px;\n" +
                            "            bottom: 198px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .p5 {\n" +
                            "            left: 106px;\n" +
                            "            bottom: 140px;\n" +
                            "        }\n" +
                            "        .left .border .p6 {\n" +
                            "            right: 52px;\n" +
                            "            bottom: 52px;\n" +
                            "        }\n" +
                            "        .left .border .p7 {\n" +
                            "            right: 52px;\n" +
                            "            bottom: 24px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .left .border .btn {\n" +
                            "\n" +
                            "            position: absolute;\n" +
                            "            top: 200px;\n" +
                            "            left: 84px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .right-shadow {\n" +
                            "            position: absolute;\n" +
                            "            right: 350px;\n" +
                            "        }\n" +
                            "\n" +
                            "        .right {\n" +
                            "            width: 350px;\n" +
                            "            height: inherit;\n" +
                            "            position: absolute;\n" +
                            "            right: 0;\n" +
                            "            /* box-shadow: 0 -3px 10px #ccc; */\n" +
                            "        }\n" +
                            "\n" +
                            "        .right img {\n" +
                            "            /* object-fit: contain; */\n" +
                            "            position: absolute;\n" +
                            "            top: 0;\n" +
                            "            right: 0;\n" +
                            "            bottom: 0;\n" +
                            "            left: 0;\n" +
                            "            margin: auto;\n" +
                            "        }\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "    <div class=\"box\">\n" +
                            "        <div class=\"left\">\n" +
                            "            <div class=\"border\">\n" +
                            "                <img src=\"./img/stamp.png\" alt=\"\" class=\"stamp\">\n" +
                            "                <img src=\"./img/logo.png\" alt=\"\" class=\"logo\">\n" +
                            "                <p class=\"p1\">尊敬的 张女士：</p>\n" +
                            "                <p class=\"p2\">您是否忘记密码？</p>\n" +
                            //"<a href='"+ConsParams.Portrait.PRIFIX_PORTRAIT+"/findPasswd/"+uuid+"' target='_blank'> 点击此链接修改密码</a>"
                            "                <a href=\""+ConsParams.Portrait.PRIFIX_PORTRAIT+"/findPasswd/"+uuid+"\" class=\"btn\" target=\"_blank\">\n" +
                            "                    <img src=\"img/btn.png\" alt=\"\">\n" +
                            "                </a>\n" +
                            "                <p class=\"p3\">*注意此按钮24小时内有效</p>\n" +
                            "                <p class=\"p4\">如若您不希望重设密码或并未请求更改密码，请忽略并删除邮件。</p>\n" +
                            "                <p class=\"p5\">谢谢！</p>\n" +
                            "                <p class=\"p6\">晋软杯官方团队</p>\n" +
                            "                <p class=\"p7\">2018.9.13</p>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "        <img src=\"./img/right-shadow.png\" alt=\"\" class=\"right-shadow\">\n" +
                            "        <div class=\"right\">\n" +
                            "            <img src=\"./img/right.png\" alt=\"\">\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>")
                    .contentType(MailContentTypeEnum.HTML)
                    .targets(new ArrayList<String>(){{
                        add(email); }}).send();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发送邮件失败");
        }
        log.info("修改密码 1：****** 邮件发送成功！");
        //把这东西存到数据库
        ChangePasswd changePasswd = new ChangePasswd();
        changePasswd.setCreateTime(new Date());
        changePasswd.setUsername(email);
        changePasswd.setUserKey(uuid);
        ChangePasswd save = changePasswdRepostory.save(changePasswd);
        if (save == null){
            return ServerResponse.createByErrorMessage("存储信息有误");
        }
        log.info("修改密码 1：****** username & valid存入数据库成功");
        return ServerResponse.createBySuccessMessage("请查收你的邮箱");
    }

    /**
     * 2.找回密码-有效校验
     */
    @Override
    @Transactional
    public ServerResponse isValid(String valid) {
        // 查询最新一条 valid 是否有效
        ChangePasswd latest = changePasswdRepostory.findLatest(valid);
        if (latest == null){
            log.info("修改密码 2：******  验证无效");
            return ServerResponse.createByErrorMessage("验证无效");
        }
        // 获取前一天 时间，判断 是否过了24h
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(latest.getCreateTime());
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        Date time = calendar.getTime();
        //比较  time 应该在 数据库获取到的时间
        if (latest.getUserKey().equals(valid) && time.after(new Date())){
            // 验证没过期,更新密码
            // 验证没过期,返回200
            log.info("修改密码 2：****** {} 验证通过",latest.getUsername());
            return ServerResponse.createBySuccess("验证通过",latest.getUsername());
        }
        log.info("修改密码 2：****** {} 验证已过期",latest.getUsername());
        return ServerResponse.createByErrorMessage("验证已过期");
    }

    /**
     * 3.找回密码-修改密码
     */
    @Override
    @Transactional
    public ServerResponse updatePasswd(String passwd,String valid) {
        // 再次验证
        ServerResponse response = isValid( valid);
        if (response.getStatus() != 200){
            log.info("修改密码 3：****** {} 验证无效",valid);
            return ServerResponse.createByErrorMessage("验证无效");
        }
        String username = (String)response.getData();
        //完成修改
        User user = new User();
        //通过 email 修改密码
        user.setUserEmail(username);
        user.setUserPasswd(passwd);
        int i = userMapper.updatePasswdByEmail(user);
        if (i!=0){
            log.info("修改密码 3：****** {} 密码修改成功",username);
            return ServerResponse.createBySuccessMessage("密码修改成功");
        }else {
            log.info("修改密码 3：****** {} 密码修改失败",username);
            return ServerResponse.createByErrorMessage("密码修改失败");
        }
    }


























    GetMeVo converse(Object[] o){
        GetMeVo userDto = new GetMeVo();
        userDto.setUserId((String)o[0]);
        userDto.setUserPhone((String)o[1]);
        userDto.setUserSchool((String)o[2]);
        userDto.setUserEmail((String)o[3]);
        userDto.setUserName((String)o[4]);
        userDto.setUserAcademy((String)o[5]);
        userDto.setUserEducation((String)o[6]);
        userDto.setUserGrade((String)o[7]);
        userDto.setUserProfession((String)o[8]);
        userDto.setUserSex((Integer)o[9]);
        userDto.setUserStuNum((String)o[10]);
        userDto.setUserPortrait((String)o[11]);
        userDto.setUserEducationId((Integer)o[12]);
        userDto.setUserSchoolId((Integer)o[13]);
        userDto.setUserStatus((Integer)o[14]);
        return userDto;
    }
}
