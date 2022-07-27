package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DogDAO;
import com.example.demo.vo.Dog;

import lombok.Setter;

@Service
@Setter
public class DogService {
	
	@Autowired
	private DogDAO dao;

	public int getNextDogNum() {
		return dao.getNextDogNum();
	}
	
	public void save(Dog d) {
		dao.save(d);
	}
	
	public void deleteByUserDog(int dog_num, int user_num) {
		dao.deleteByUserDog(dog_num, user_num);
	}
	
	public List<Dog> findByDogUserNum(int user_num){
		return dao.findByUserNum(user_num);
	}
	public List<Dog> findAll(){
		return dao.findAll();
	}
	
	
	
	
}