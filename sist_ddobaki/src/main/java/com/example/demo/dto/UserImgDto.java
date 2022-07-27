//package com.example.demo.dto;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.vo.UserImg;
//import com.example.demo.vo.UserInfo;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@RequiredArgsConstructor
//public class UserImgDto {
//    private MultipartFile file;
//	private String folder_path;
//	private String store_file_name;
//
//    public UserImg toEntity(String user_file, UserInfo user) {
//    	 return UserImg.builder()
//                 .user(user)
//                 .user_file(user_file)
//                 .build();
//    }
//}