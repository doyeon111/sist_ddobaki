package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DibsDAO;
import com.example.demo.vo.Board;
import com.example.demo.vo.Dibs;

import lombok.Setter;

@Service
@Setter
public class DibsService {
	
	@Autowired
	private DibsDAO dao;

	public int getNextDibNum() {
		return dao.getNextDibNum();
	}
	
	public void save(Dibs d) {
		dao.save(d);
	}
	
	public void deleteByUserPlace(int place_num, int user_num) {
		dao.deleteByUserPlace(place_num, user_num);
	}
	
	public List<Dibs> findByUserNum(int user_num){
		return dao.findByUserNum(user_num);
	}
	public List<Dibs> findByPlaceNum(int place_num){
		return dao.findByPlaceNum(place_num);
	}
	
}