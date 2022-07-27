package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.UserInfoDAO;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.vo.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Setter
@RequiredArgsConstructor
public class UserInfoService {
	@Autowired
	private UserInfoDAO dao;
	
	public List<UserInfo> findAll(){
		return dao.findAll();
	}
	
	public UserInfo getUser(int user_num) {
		return dao.findById(user_num).get();
	}
	
	public int getNextUserNum() {
		return dao.getNextUserNum();
	}

	public void deleteUser(int user_num) {
		dao.deleteByUserNum(user_num);
	}
	
	public UserInfo getOne(int user_num) {
		return dao.getOne(user_num);
	}
	
	public void save(UserInfo u) {
		dao.save(u);
	}
	
	public UserInfo findByUser_id(String user_id) {
		return dao.findByUser_id(user_id);
	}
	

	public void update(String user_file, int user_num) {
		dao.update(user_file, user_num);
	}

}
