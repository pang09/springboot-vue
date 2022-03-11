package com.example.springbootvue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootvue.entity.Admin;
import com.example.springbootvue.entity.R;

import javax.servlet.http.HttpServletRequest;

public interface AdminService extends IService<Admin> {

    R login(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUserName(String username);

    Admin findUserInfo(Integer uid);

    R updateUserInfo(Admin admin);
}
