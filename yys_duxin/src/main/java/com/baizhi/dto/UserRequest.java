package com.baizhi.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String phone;
    private String code;
    private String password;
}
