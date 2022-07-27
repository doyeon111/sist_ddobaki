package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlaceDAO;
import com.example.demo.dto.PlaceSearchCondition;
import com.example.demo.vo.Place;

import lombok.Setter;

@Service
@Setter
public class PlaceService {

//	public void setDao(PlaceRepository dao) {
//		this.dao = dao;
//	}
//	

	@Autowired
	private PlaceDAO dao;

	public List<Place> findAll() {
		return dao.findAll();
	}

	public List<Place> findByCondition(PlaceSearchCondition condition) {
		return dao.searchTest(condition);
	}

	public Place getPlace(int place_num) {
		return dao.findById(place_num).get();
	}

//	public Place getPlace(Integer place_num) {
//		return dao.getById(place_num);
//	}
	@Transactional
	public int updateHit(int place_num) {
		return dao.updateHit(place_num);
	}

	public List<Place> findByRegionNum(int place_region_num) {
		return dao.findByRegionNum(place_region_num);
	}

	// 지도카드1차 + 리뷰검색
	public List<Place> placeNameList(int place_type_num, int place_region_num) {
		return dao.placeNameList(place_type_num, place_region_num);
	}

}
