package com.tyut.web.controller;

        import com.alibaba.dubbo.config.annotation.Reference;
        import com.google.common.collect.Lists;
        import com.tyut.core.constants.ConsParams;
        import com.tyut.core.dto.UserUpdateVo;
        import com.tyut.core.pojo.User;
        import com.tyut.core.response.ServerResponse;
        import com.tyut.core.utils.FTPUtil;
        import com.tyut.user.service.UserService;
        import com.tyut.web.util.Base64ToFile;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.BeanUtils;
        import org.springframework.security.core.Authentication;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.ModelAndView;
        import sun.misc.BASE64Decoder;
        import sun.misc.BASE64Encoder;

        import javax.servlet.http.HttpServletRequest;
        import java.io.*;
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
        BeanUtils.copyProperties(userUpdateVo,user);
        user.setUserUpdateTime(new Date());
        return userService.update(user);
    }

    @ApiOperation("修改/上传头像")
    @PostMapping("/updatePortrait")
    public ServerResponse updatePortrait(Authentication user,@RequestParam String imgStr
                                            ,HttpServletRequest request) throws IOException {
        //没有用户信息
        if (StringUtils.isEmpty(user)){ return ServerResponse.createByErrorMessage("请先登录"); }
        // 图像数据为空
        if (imgStr == null) { return ServerResponse.createByErrorMessage("图像数据为空"); }
        String path = request.getSession().getServletContext().getRealPath("upload");
        return userService.uploadPortrait(user.getName(),imgStr,path);
    }

    @ApiOperation("上传证明(图片)")
    @PostMapping("/uploadFile")
    public ServerResponse uploadCertificate(Authentication user,@RequestParam String imgStr
            ,@RequestParam String type, HttpServletRequest request) throws IOException {
        //没有用户信息
        if (StringUtils.isEmpty(user)){ return ServerResponse.createByErrorMessage("请先登录"); }
        // 图像数据为空
        if (imgStr == null) { return ServerResponse.createByErrorMessage("图像数据为空"); }
        String path = request.getSession().getServletContext().getRealPath("upload");
        return userService.uploadFile(user.getName(),imgStr,path,type);
    }


    /**
     * 修改密码第一步
     * 发送email，并保存valid 和username
     */
    @ApiOperation("修改密码（发送email） 格式：/getEmail?username=15235951681")
    @GetMapping("/getEmail")
    public ServerResponse sendEmail(@RequestParam String username){
        if (username == null){
            return ServerResponse.createByErrorMessage("用户账号不能为空");
        }
        return userService.findPasswd(username);
    }

    /**
     * 修改密码第二步
     * 用户点击邮件，通过valid校验有效 后跳转到修改密码页面
     * 校验 valid 真实性
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
}
