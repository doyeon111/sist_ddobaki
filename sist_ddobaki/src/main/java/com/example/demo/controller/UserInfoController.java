package com.example.demo.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UserInfoService;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Controller
@Setter
public class UserInfoController {
	@Autowired
	private UserInfoService us;
	
	
	@GetMapping("/listUser")
	@ResponseBody
	public List<UserInfo> list() {
		return us.findAll();
	}
	
	@ResponseBody
    @GetMapping("/getUser/{user_num}")
    public UserInfo getUser(@PathVariable int user_num) {
        UserInfo userInfo = us.getUser(user_num);
        return userInfo;
    }
	
	@PostMapping("/updateUserInfo")
	public String insert(@RequestParam("uploadFile") MultipartFile uploadFile,Authentication auth) throws IOException {

		
		UserInfo user=us.findByUser_id(auth.getName());
		int user_num=user.getUser_num();
		
		if(uploadFile!=null) {

		String uploadFileName=uploadFile.getOriginalFilename();
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid+uploadFileName.substring(uploadFileName.lastIndexOf("."));
		System.out.println("변환된 파일명:"+fileName);
		
		//파일 다운로드
        File converFile = new File("/Users/feelj/userImage", fileName);
		try {
			uploadFile.transferTo(converFile); 
		}catch(Exception e) {
			System.out.println("에러:"+e.getMessage());
		}
		
			us.update(fileName, user_num);	
		}else {
			System.out.println("이미지 수정 안함");
		}
		return "redirect:/myPage";
	}
}
