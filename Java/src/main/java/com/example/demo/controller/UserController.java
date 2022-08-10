package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    public Result update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result update(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }
}
