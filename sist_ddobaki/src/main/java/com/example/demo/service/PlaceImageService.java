package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlaceDAO;
import com.example.demo.dao.PlaceImageDAO;
import com.example.demo.dto.PlaceSearchCondition;
import com.example.demo.vo.Board;
import com.example.demo.vo.Place;
import com.example.demo.vo.PlaceImage;

import lombok.Setter;

@Service
@Setter
public class PlaceImageService {


	@Autowired
	private PlaceImageDAO dao;

	public List<PlaceImage> getPlaceImage(int place_num) {
		return dao.findByPlaceNum(place_num);
	}
}
