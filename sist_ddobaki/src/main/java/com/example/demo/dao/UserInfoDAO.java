package com.example.demo.dao;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.UserInfo;

@Repository
public interface UserInfoDAO extends JpaRepository<UserInfo, Integer> {
	

	@Query(value="select * from UserInfo where user_id=:user_id", nativeQuery=true)
	public UserInfo findByUser_id(String user_id);
	
	@Query(value="select nvl(max(user_num),0)+1 from UserInfo", nativeQuery=true)
	public int getNextUserNum();
	
//	@Transactional
//	@Query(value="insert into UserInfo u(u.user_num, u.user_id, u.user_file, u.user_nick, u.user_pwd, u.user_phone, u.user_role) values(:#{#u.user_num}, :#{#u.user_id}, :#{#u.user_file}, :#{#u.user_nick}, :#{#u.user_pwd},"
//			+ " :#{#u.user_phone}, :#{#u.user_role})", nativeQuery=true)
//	@Modifying
//	public Optional<UserInfo> insertUser(@Param("user") UserInfo user);

	@Modifying
	@Transactional
	@Query(value="delete UserInfo where user_num=:user_num",nativeQuery=true)
	public void deleteByUserNum(int user_num);
	
//	@Transactional
//	@Query(value="update UserInfo u set u.user_file=:#{#u.user_file}, u.user_nick=:#{#u.user_nick}, u.user_phone=:#{#u.user_phone}", nativeQuery=true)
//	@Modifying
//	public void update(@Param("user_file") String user_file, @Param("user_nick") String user_nick, @Param("user_phone") String user_phone);

	
	@Modifying
	@Transactional
	@Query(value = "update UserInfo u set u.user_file=?1 where u.user_num=?2", nativeQuery = true)
	public void update(String user_file, int user_num);

}

