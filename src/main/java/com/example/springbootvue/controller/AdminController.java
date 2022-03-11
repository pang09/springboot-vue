package com.example.springbootvue.controller;

import com.example.springbootvue.entity.Admin;
import com.example.springbootvue.entity.AdminLogin;
import com.example.springbootvue.entity.R;
import com.example.springbootvue.service.AdminService;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private Producer producer;

    @ApiOperation(value = "登录之后返回Token")
    @PostMapping("/login")
    public R login(@RequestBody AdminLogin adminLogin,HttpServletRequest request){
        return adminService.login(adminLogin.getUsername(),adminLogin.getPassword(),adminLogin.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (null==principal) {
            return null;
        }
        String username=principal.getName();
        Admin admin=adminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public R logout(){
        return R.ok().message("注销成功!");
    }


    @ApiOperation(value = "返回用户信息")
    @GetMapping("/user/findUserInfo/{uid}")
    public Admin findUserInfo(@PathVariable Integer uid){
        return adminService.findUserInfo(uid);
    }

    @ApiOperation(value = "返回用户信息")
    @PostMapping("/user/updateUserInfo")
    public R updateUserInfo(@RequestBody Admin admin){
        return adminService.updateUserInfo(admin);
    }


    @ApiOperation(value = "生成验证码图片")
    @GetMapping(value = "captchat")
    public void createKacptcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store");
        response.setContentType("image/jpeg");
        // 文字验证码
        String text = producer.createText();
        // 图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存验证码到session
        request.getSession().setAttribute("verify",text);
        System.out.println(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        IOUtils.closeQuietly(outputStream);
    }
}
