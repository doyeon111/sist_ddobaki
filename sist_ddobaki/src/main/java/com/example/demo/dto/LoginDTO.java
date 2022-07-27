package com.example.demo.dto;

import com.example.demo.vo.UserInfo;

public class LoginDTO {

		private String user_id;
	    private String user_pwd;
	  
	    public UserInfo toEntity() {
	        return UserInfo.builder()
	                .user_id(user_id)
	                .user_pwd(user_pwd)
	                .build();
	    }
	}