package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Dibs;
import com.example.demo.vo.Dog;

@Repository
public interface DogDAO extends JpaRepository<Dog, Integer> {
	// 강아지 번호 자동부여
	@Query("select nvl(max(dog_num),0)+1 from Dog")
	public int getNextDogNum();
	
	@Query(value="select * from Dog where user_num=:user_num",nativeQuery = true)
	public List<Dog> findByUserNum(int user_num);
	
	
	@Modifying
	@Transactional
	@Query(value="delete from Dog d where d.dog_num=:dog_num and d.userinfo.user_num=:user_num")
	void deleteByUserDog(int dog_num, int user_num);
	
}
