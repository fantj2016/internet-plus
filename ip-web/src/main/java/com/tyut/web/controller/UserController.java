package com.tyut.web.controller;

        import com.alibaba.dubbo.config.annotation.Reference;
        import com.google.common.collect.Lists;
        import com.tyut.core.constants.ConsParams;
        import com.tyut.core.dto.UserUpdateVo;
        import com.tyut.core.pojo.User;
        import com.tyut.core.response.ServerResponse;
        import com.tyut.core.utils.FTPUtil;
        import com.tyut.user.service.UserService;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.security.core.Authentication;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.ModelAndView;

        import javax.servlet.http.HttpServletRequest;
        import java.io.File;
        import java.io.IOException;
        import java.util.Date;
        import java.util.UUID;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

/**
 * Created by Fant.J.
 * 2018/4/23 19:20
 */

@Api(value = "/UserController",description = "用户登录接口")
@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {

    @Reference(version = "2.0.0")
    private UserService userService;

    @GetMapping("/me")
    public ServerResponse getCurrentUser(Authentication user)  {
        if (StringUtils.isEmpty(user)){
            return ServerResponse.createByErrorMessage("请先登录");
        }
        String username = user.getName();
        log.info("从Authentication里获取到的username为{}",username);
        return userService.selectMe(username);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ServerResponse updateUser(UserUpdateVo userUpdateVo){
        User user = new User();
        user.setUserName(userUpdateVo.getUserName());
        user.setUserAcademy(userUpdateVo.getUserAcademy());
        user.setUserId(userUpdateVo.getUserId());
        user.setUserUpdateTime(new Date());
        user.setUserPhone(userUpdateVo.getUserPhone());
        user.setUserSchoolId(userUpdateVo.getUserSchoolId());
        user.setUserStuNum(userUpdateVo.getUserStuNum());
        user.setUserGrade(userUpdateVo.getUserGrade());
        user.setUserProfession(userUpdateVo.getUserProfession());
        user.setUserEducation(userUpdateVo.getUserEducation());
        return userService.update(user);
    }

    @ApiOperation("修改头像")
    @PostMapping("/updatePortrait")
    @Transactional
    public ServerResponse upload(@RequestParam(value = "file",required = false) MultipartFile file,
                                 HttpServletRequest request,Authentication user){

        if (StringUtils.isEmpty(user)){
            return ServerResponse.createByErrorMessage("请先登录");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String name = uploadFile(file, path);
        User user1 = new User();
        user1.setUserPortrait(ConsParams.Portrait.PRIFIX_PORTRAIT+"/image/"+name);
        log.info("修改后的用户头像地址：{}",user1.getUserPortrait());
        return userService.uploadPortrait(user.getName(),user1);
    }

    /**
     * 修改密码第一步
     * 发送email，并保存valid 和username
     */
    @ApiOperation("修改密码（发送email）")
    @PostMapping("/getEmail")
    public ServerResponse sendEmail(@RequestParam String username){
        return userService.findPasswd(username);
    }

    /**
     * 修改密码第二步
     * 用户点击邮件，通过valid校验有效 后跳转到修改密码页面
     * 校验 valid 真实性
     * @return
     */
    @GetMapping("/findPasswd/{valid}")
    public ModelAndView findPasswd(
            @PathVariable String valid,
            ModelAndView mv){
        // 检测 valid 是否有效  ，并返回username
        ServerResponse serverResponse = userService.isValid(valid);
        log.info("状态码{}",serverResponse.getStatus());
        if (serverResponse.getStatus()!=200){
            //设置msg 并返回view
            mv.addObject("msg","验证无效，请重新获取邮件");
            log.info("返回错误页面");
            mv.setViewName("errorPage");
            return mv;
        }
        //返回 修改页面的 view
        mv.setViewName("changePasswd");
        //把username 和 valid 返回给页面
        mv.addObject("valid",valid);
        return mv;
    }
    /**
     * 修改密码第三步
     * 接受post
     */
    @PostMapping("/changePasswd")
    public ModelAndView changePasswd(
            @RequestParam String passwd,
            @RequestParam String rePasswd,
            @RequestParam String valid,
            ModelAndView mv){

        userService.updatePasswd(passwd,valid);
        mv.addObject("msg","密码修改成功!");
        mv.setViewName("success");
        return mv;
    }





    /**
     * 判断是 图片文件
     */
    private static boolean isImage(String fileName){
        String reg = "(jpg)|(png)|(gif)|(bmp)|(GIF)|(JPG)|(PNG)|(JPEG)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fileName);
        boolean b = matcher.find();
        log.info("图片验证结果：{}",b);
        return b;
    }

    /**
     * 判断是 压缩文件
     */
    private static boolean isZip(String fileName) {
        String reg = "(RAR)|(ZIP)|(7Z)|(GZ)|(BZ)|(ACE)|(UHA)|(UDA)|(ZPAQ)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fileName.toUpperCase());
        boolean b = matcher.find();
        log.info("图片验证结果：{}", b);
        return b;
    }
    /**
     * 上传头像
     */
    public static String uploadFile(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        log.info("获取到的文件是{},后缀是{}",fileName,fileExtensionName);
        if (!isImage(fileExtensionName)){
            log.info("图片格式错误{}");
            //这里返回异常
//            return "图片格式错误！";
        }
        String uploadFileName = UUID.randomUUID().toString().replace("-","")+"."+fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            FTPUtil.uploadImage(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
//            return "上传文件异常";
        }
        String name = targetFile.getName();
        log.info("targetFile.getName():{}",name);
        return name;
    }
}
