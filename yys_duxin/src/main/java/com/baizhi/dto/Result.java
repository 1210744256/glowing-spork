package com.baizhi.dto;

import lombok.Data;

@Data
public class Result {
    private Boolean success;
    private Object data;
    private String msg;
    public Result ok(){
        setSuccess(true);
        return this;
    }
    public Result ok(Object data){
        setSuccess(true);
        setData(data);
        return this;
    }
    public Result ok(Object data,String msg){
        setSuccess(true);
        setData(data);
        setMsg(msg);
        return this;
    }
    public Result error(){
        setSuccess(false);
        return this;
    }
    public Result error(Object data){
        setSuccess(false);
        setData(data);
        return this;
    }
    public Result error(Object data,String msg){
        setSuccess(false);
        setData(data);
        setMsg(msg);
        return this;
    }
}
