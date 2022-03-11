package com.example.springbootvue.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.springbootvue.config.JwtTokenUtil;
import com.example.springbootvue.entity.Admin;
import com.example.springbootvue.entity.AdminLogin;
import com.example.springbootvue.entity.R;
import com.example.springbootvue.mapper.AdminMapper;
import com.example.springbootvue.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public R login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("verify");
        System.out.println(captcha);
        if (!StringUtils.hasText(code) || !captcha.equalsIgnoreCase(code)){
            return R.error().message("验证码错误，请重新输入");
        }
        System.out.println("ppp");
//        UserDetails userDetails =userDetailsService.loadUserByUsername(username);
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Admin userDetails= adminMapper.selectOne(wrapper);
        System.out.println(userDetails);
        if (null==userDetails || passwordEncoder.matches(password,userDetails.getPassword())){
            return R.error().message("用户名或密码错误");
        }
//        if (!userDetails.isEnabled()){
//            return R.error().message("账号已被禁用");
//        }
//        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken
//                (userDetails,null,userDetails.getAuthorities());
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("username",username);
//        Admin admin = adminMapper.selectOne(wrapper);
//        if (null==admin || !admin.getPassword().equals(password)){
//            return R.error().message("用户名或密码不正确");
//        }

        String token =jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return R.ok().message("登录成功").data("tokenMap",tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public Admin findUserInfo(Integer uid) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("uid",uid));
    }

    @Override
    public R updateUserInfo(Admin admin) {
        adminMapper.updateById(admin);
        return R.ok();
    }
}
