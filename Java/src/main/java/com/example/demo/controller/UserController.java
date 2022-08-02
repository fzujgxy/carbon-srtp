package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: Java
 * @author: HarryGao
 * @create: 2022-08-01 15:28
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/register")
    public Result save(@RequestBody User user){
        if(userMapper.samePhone(user.getPhone()).size() != 0){
            return Result.error("400","此手机号已被注册");
        }else{
            userMapper.insert(user);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getPhone,user.getPhone()).eq(User::getPassword,user.getPassword()));
        if (res == null){
            return Result.error("400","账号或密码错误");
        }
        return Result.success();
    }
}
