package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Board;
import com.example.demo.vo.Dibs;

@Repository
public interface DibsDAO extends JpaRepository<Dibs, Integer> {
	// 찜 번호 자동부여
	@Query("select nvl(max(dib_num),0)+1 from Dibs")
	public int getNextDibNum();
	
	@Query(value="select * from Dibs where user_num=:user_num",nativeQuery = true)
	public List<Dibs> findByUserNum(int user_num);
	
	@Query(value="select * from Dibs where place_num=:place_num",nativeQuery = true)
	public List<Dibs> findByPlaceNum(int place_num);
	
	@Modifying
	@Transactional
	@Query(value="delete from Dibs d where d.place.place_num=:place_num and d.userinfo.user_num=:user_num")
	void deleteByUserPlace(int place_num, int user_num);
	
}
