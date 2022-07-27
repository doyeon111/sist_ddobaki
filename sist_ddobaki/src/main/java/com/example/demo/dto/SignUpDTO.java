package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.vo.UserInfo;

import lombok.Data;

@Data
public class SignUpDTO {
	private String user_id;
    private String user_pwd;
    private String user_nick;
    private String user_phone;
    private String user_file;
    private MultipartFile userFile;

    public UserInfo toEntity() {
        return UserInfo.builder()
                .user_id(user_id)
                .user_pwd(user_pwd)
                .user_nick(user_nick)
                .user_phone(user_phone)
                .user_file(user_file)
                .build();
    }
}