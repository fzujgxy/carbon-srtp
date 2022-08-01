package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: Java
 * @author: HarryGao
 * @create: 2022-08-01 15:30
 */

@TableName("user")
@Data
public class User {
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String email;
    private String phone;
}
