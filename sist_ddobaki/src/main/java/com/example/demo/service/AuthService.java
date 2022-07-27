package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.PrincipalDetails;
import com.example.demo.dao.UserInfoDAO;
import com.example.demo.vo.UserInfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private final UserInfoDAO dao;
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserInfoService uis;
	
	

	@Transactional // Write(Insert, Update, Delete)
	public UserInfo signup(UserInfo user) {
		String rawPassword = user.getUser_pwd();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setUser_pwd(encPassword);
		user.setUser_role("USER");
		user.setUser_num(uis.getNextUserNum());
		UserInfo userEntity = dao.save(user);
		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {		
		UserInfo userEntity = dao.findByUser_id(user_id);

        if(userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }
    }
}

	
//	@Transactional
//	public void joinUser(UserInfo user){
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setUser_pwd(passwordEncoder.encode(user.getUser_pwd()));
//		dao.save(user);
//	}
//	
//	// 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
//	@Override  
//	public UserDetails loadUserByUsername(String user_id) { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
//	    return (UserDetails) dao.findByUser_id(user_id);
//	    		//.orElseThrow(() -> new UsernameNotFoundException((user_id)));
//	  }
