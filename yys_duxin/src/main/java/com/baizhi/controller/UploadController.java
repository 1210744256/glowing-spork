package com.baizhi.controller;

import com.baizhi.dto.Result;
import com.baizhi.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private BlogService blogService;
//    上传blog图片
    @PostMapping("/blog")
    public Result uploadBlog(MultipartFile file, HttpServletRequest request) throws IOException {
//        动态获取上传路径
        String realPath = request.getServletContext().getRealPath("/upload");
//        获取文件的路径以及路径名
        String filename = file.getOriginalFilename();
        String uuid= UUID.randomUUID().toString().replaceAll("-","").substring(0,16);
//        将uuid作为文件名
        filename=uuid+"."+filename.split("\\.")[1];
        File file1=new File(realPath,filename);
        log.debug("复制的文件名{}:"+file1);
        file.transferTo(file1);
        return new Result().ok(file1.getName());
    }
}
