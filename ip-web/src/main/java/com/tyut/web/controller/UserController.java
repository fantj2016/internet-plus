package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import com.tyut.core.constants.ConsParams;
import com.tyut.core.pojo.User;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.utils.PropertiesUtil;
import com.tyut.core.vo.UserVo;
import com.tyut.user.service.UserEduService;
import com.tyut.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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
    public ServerResponse getCurrentUser(Authentication user) throws UnsupportedEncodingException {

        String username = user.getName();
        log.info("从Authentication里获取到的username为{}",username);
        return userService.selectMe(username);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ServerResponse updateUser(User user){
        return userService.update(user);
    }

    @ApiOperation("修改头像")
    @PostMapping("/updatePortrait")
    public ServerResponse upload(@RequestParam(value = "file",required = false) MultipartFile file,
                                 HttpServletRequest request,Authentication user){
        String path = request.getSession().getServletContext().getRealPath("upload");
        return userService.uploadFile(file,path,user.getName());
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
//        if (passwd != rePasswd){
//            log.info("密码{},重复密码{}",passwd,rePasswd);
//            mv.addObject("msg","两次密码输入不同");
//            mv.setViewName("errorPage");
//            return mv;
//        }
        userService.updatePasswd(passwd,valid);
        mv.addObject("msg","密码修改成功!");
        mv.setViewName("success");
        return mv;
    }
}
