package com.baizhi.controller;

import com.baizhi.entity.Clazz;
import com.baizhi.service.ClazzService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@EnableWebMvc
@RequestMapping("/clazz")
@ResponseBody
public class ClazzController {
        Logger logger = LoggerFactory.getLogger(ClazzController.class);
    @Autowired
    private ClazzService clazzService;
    @RequestMapping("/queryAll")
    public List<Clazz> queryAll(){
        return clazzService.queryAll();
    }
    @RequestMapping("/deleteById")
    public void deleteById(int id,String path){
        logger.debug("path:"+path);
        File file = new File("/springmvc_work/",path);
        logger.debug("file:"+file.getAbsolutePath());
        file.delete();
        clazzService.deleteById(id);
    }
    @RequestMapping("/insert")
    public void insert(String clazz1, MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        logger.debug("clazz1:"+clazz1);
        Clazz clazz = new ObjectMapper().readValue(clazz1, Clazz.class);
        logger.debug("mult:"+multipartFile.getContentType());
//        上传
        String originalFilename = multipartFile.getOriginalFilename();
//        动态获取路径
        String realPath = request.getServletContext().getRealPath("/upload");
//        获取uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
//        uuid
        uuid=uuid+"."+originalFilename.split("\\.")[1];
        clazz.setPath("/upload/"+uuid);
        multipartFile.transferTo(new File(realPath,uuid));
        clazzService.insert(clazz);
    }
    @RequestMapping("/queryId")
    public Clazz queryById(int id) {
        return clazzService.queryById(id);
    }
    @RequestMapping("/clazzExcel")
    public Map<String,Object> clazzExcel(){
        return clazzService.clazzExcel();
    }
}
