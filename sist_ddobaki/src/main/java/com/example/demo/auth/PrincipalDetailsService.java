package com.example.demo.auth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoDAO;
import com.example.demo.vo.UserInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserInfoDAO dao;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {

        UserInfo userEntity = dao.findByUser_id(user_id);

        if(userEntity == null) {
           throw new UsernameNotFoundException(user_id);
        } 
        
        return new PrincipalDetails(userEntity);
    }
}