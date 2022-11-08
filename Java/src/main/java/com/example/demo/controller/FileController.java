package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;


import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: Java
 * @author: HarryGao
 * @create: 2022-08-01 15:28
 */

@RestController
@RequestMapping("/upload")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;


//    @PostMapping("/upload")
//    public Result upload(String type,@RequestParam MultipartFile novel,@RequestParam MultipartFile brief,@RequestParam MultipartFile picture) throws IOException {
    @PostMapping("/files/{fileName}")
    public Result upload( @RequestParam MultipartFile files,@PathVariable String fileName) throws IOException {

        String originalNovelName = files.getOriginalFilename();
        String filesType = FileUtil.extName(originalNovelName);
        long filesSize = files.getSize();

        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);

        //判断配置文件的目录是否存在，若不存在则创建一个新的文件目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }

        //定义一个文件唯一的标识码
        String names = "{fileName}" + StrUtil.DOT + filesType;
        File uploadFile = new File(fileUploadPath + names);

        //把获取后的文件存储到磁盘目录
        files.transferTo(uploadFile);

        return Result.success();
    }
}