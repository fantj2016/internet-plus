package com.tyut.user.service.impl;

import com.tyut.core.constants.ConsParams;
import com.tyut.core.pojo.User;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.utils.FTPUtil;
import com.tyut.core.vo.UserVo;
import com.tyut.user.dao.UserMapper;
import com.tyut.user.dto.UserDto;
import com.tyut.user.repostory.UserRepository;
import com.tyut.user.service.UserService;
import com.tyut.user.util.CheckFormat;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public ServerResponse<UserVo> selectById(int id) {
        User user = userRepository.findOne(id);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到该用户");
        }
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserName(user.getUserName());
        userVo.setUserPhone(user.getUserPhone());
        userVo.setUserSchoolId(user.getUserSchoolId());
        return ServerResponse.createBySuccess(userVo);
    }

    /**
     * 根据email查询密码
     *
     * @param email
     */
    @Override
    public String selectPasswdByEmail(String email) {
        return userRepository.selectPasswdByEmail(email);
    }

    /**
     * 根据 phone 查询密码
     *
     * @param phone
     */
    @Override
    public String selectPasswdByPhone(String phone) {
        return userRepository.selectPasswdByPhone(phone);
    }

    /**
     * 判断 email 是否存在
     *
     * @param email
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
     *
     * @param phone
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
     *
     * @param str
     */
    @Override
    public ServerResponse selectMe(String str) {
        if (CheckFormat.isEmail(str)){
//            UserDto user = userRepository.selectByEmail(str);

            Query nativeQuery = entityManager.createNativeQuery("select u.user_id,u.user_phone,s.school_name as user_school,u.user_email,u.user_name,u.user_academy,u.user_education,u.user_grade,u.user_profession,u.user_sex,u.user_stu_num,u.user_portrait  from ip_user as u,ip_school as s where u.user_school_id=s.id and u.user_email=?1");
            Object result = nativeQuery.setParameter(1, str).getSingleResult();
            Object[] o = (Object[]) result;
            UserDto userDto = new UserDto();
            userDto.setUserId((Integer)o[0]);
            userDto.setUserPhone((String)o[1]);
            userDto.setUserSchool((String)o[2]);
            userDto.setUserEmail((String)o[3]);
            userDto.setUserName((String)o[4]);
            userDto.setUserAcademy((String)o[5]);
            userDto.setUserEducation((Integer)o[6]);
            userDto.setUserGrade((String)o[7]);
            userDto.setUserProfession((String)o[8]);
            userDto.setUserSex((Integer)o[9]);
            userDto.setUserStuNum((String)o[10]);
            userDto.setUserPortrait((String)o[11]);
            if (userDto == null){
                return ServerResponse.createByErrorMessage("该账号不存在");
            }
            return ServerResponse.createBySuccess(userDto);
        }
        if (CheckFormat.isPhone(str)){
//            UserDto user = userRepository.selectByPhone(str);
            Query nativeQuery = entityManager.createNativeQuery("select u.user_id,u.user_phone,s.school_name as user_school,u.user_email,u.user_name,u.user_academy,u.user_education,u.user_grade,u.user_profession,u.user_sex,u.user_stu_num,u.user_portrait  from ip_user as u,ip_school as s where u.user_school_id=s.id and u.user_phone=?1");
            Object result = nativeQuery.setParameter(1, str).getSingleResult();
            Object[] o = (Object[]) result;
            UserDto userDto = new UserDto();
            userDto.setUserId((Integer)o[0]);
            userDto.setUserPhone((String)o[1]);
            userDto.setUserSchool((String)o[2]);
            userDto.setUserEmail((String)o[3]);
            userDto.setUserName((String)o[4]);
            userDto.setUserAcademy((String)o[5]);
            userDto.setUserEducation((Integer)o[6]);
            userDto.setUserGrade((String)o[7]);
            userDto.setUserProfession((String)o[8]);
            userDto.setUserSex((Integer)o[9]);
            userDto.setUserStuNum((String)o[10]);
            userDto.setUserPortrait((String)o[11]);

            if (userDto == null){
                return ServerResponse.createByErrorMessage("该账号不存在");
            }
            log.info(userDto.toString());
            return ServerResponse.createBySuccess(userDto);
        }
        log.info(str);
        return ServerResponse.createByErrorMessage("用户名格式不正确");
    }

    /**
     * 上传头像和文件
     *
     * @param file
     * @param path
     */
    @Override
    public ServerResponse uploadFile(MultipartFile file, String path,String username) {
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        log.info("获取到的文件是{},后缀是{}",fileName,fileExtensionName);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功了
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
            return ServerResponse.createByErrorMessage("上传文件异常");
        }
        String name = targetFile.getName();
        log.info("targetFile.getName():{}",name);
        //存入数据库  FTPUtil.getFtpIp()
        User user = new User();
        user.setUserPortrait(ConsParams.Portrait.PRIFIX_PORTRAIT+"/iamge"+name);
        log.info("修改后的用户头像地址：{}",user.getUserPortrait());
        if (CheckFormat.isPhone(username)){
            user.setUserPhone(username);
            userMapper.updatePortraitByPhone(user);
        }else if (CheckFormat.isEmail(username)){
            user.setUserEmail(username);
            userMapper.updatePortraitByEmail(user);
        }
        return null;
    }

}
