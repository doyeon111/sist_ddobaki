package com.example.demo.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.vo.UserInfo;

public class PrincipalDetails implements UserDetails {
	// 스프링시큐리티
	private UserInfo user;

	public PrincipalDetails(UserInfo user) {
		this.user = user;
	}

//	// 사용자의 권한을 콜렉션 형태로 반환
//	// 단, 클래스 자료형은 GrantedAuthority를 구현해야함
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<GrantedAuthority> roles = new HashSet<>();
//		for (String role : user_role.split(",")) {
//			roles.add(new SimpleGrantedAuthority(role));
//		}
//		return roles;
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> {
			return user.getUser_role();
		}); // 람다식

		return collector;
	}

	// 사용자의 id를 반환 (unique한 값)
	@Override
	public String getUsername() {
		return user.getUser_id();
	}

	// 사용자의 password를 반환
	@Override
	public String getPassword() {
		return user.getUser_pwd();
	}

	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		// 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금되었는지 확인하는 로직
		return true; // true -> 잠금되지 않았음
	}

	// 패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		// 계정이 사용 가능한지 확인하는 로직
		return true; // true -> 사용 가능
	}
}