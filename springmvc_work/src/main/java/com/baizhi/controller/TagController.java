package com.baizhi.controller;

import com.baizhi.dto.TagResponse;
import com.baizhi.entity.Tag;
import com.baizhi.service.TagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/tag")
@ResponseBody
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping("/queryAll")
    public List<Tag> queryAll() {
       return tagService.queryAll();
    }
    @RequestMapping("/deleteById")
    public void deleteById(int id) {
     tagService.deleteById(id);
        //        修改标签后绘制图表
        List<TagResponse> tagResponses = tagService.queryEchas();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(tagResponses);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        GoEasy goEasy = new GoEasy("https://rest-hz.goeasy.io", "BC-3618fc7f5fb940568c6727366eec1bb1");
        goEasy.publish("tag_channel", json, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("Publish success.");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.println("Failed to Publish message, error:" + error.getCode() + " , " + error.getContent());
            }
        });
    }
    @RequestMapping("/insert")
    public void insert(@RequestBody Tag tag) {
        tag.setCreateDate(new Date());
        tagService.insert(tag);
        //        修改标签后绘制图表

        List<TagResponse> tagResponses = tagService.queryEchas();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(tagResponses);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        GoEasy goEasy = new GoEasy("https://rest-hz.goeasy.io", "BC-3618fc7f5fb940568c6727366eec1bb1");
        goEasy.publish("tag_channel", json, new PublishListener() {
            @Override
            public void onSuccess() {
                System.out.println("Publish success.");
            }

            @Override
            public void onFailed(GoEasyError error) {
                System.out.println("Failed to Publish message, error:" + error.getCode() + " , " + error.getContent());
            }
        });
    }
    @RequestMapping("/queryTypeAll")
    public List<Tag> queryTypeAll(String type) {
        return tagService.queryTypeAll(type);
    }
    @RequestMapping("/queryEchas")
    public List<TagResponse> queryEchas(){
       return tagService.queryEchas();
    }
}
