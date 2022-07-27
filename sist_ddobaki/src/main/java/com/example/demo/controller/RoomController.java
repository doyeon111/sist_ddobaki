package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Room2DAO;
import com.example.demo.service.PlaceService;
import com.example.demo.service.RoomService;
import com.example.demo.vo.Place;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.Room;
import com.example.demo.vo.RoomVO;

import lombok.Setter;

@Controller
@Setter
public class RoomController {
	@Autowired
	private RoomService roomS;

	@Autowired
	private PlaceService ps;
	
	@Autowired
	private Room2DAO dao;
	
	
	@GetMapping("/listRoom")
	@ResponseBody
	public List<Room> list() {
		return roomS.findAll();
	}
	
	@ResponseBody
	@GetMapping("/getRoom/{room_num}")
	public Room getRoom(@PathVariable int room_num) {
		Room room = roomS.getRoom(room_num);
		return room;
	}
	
	
	
//	@GetMapping("/listRoom1")
//	@ResponseBody
//	public List<RoomVO> findAll() {
//		return dao.findAll();
//	}
	
	
	//ajax를 위한 장소번호, 체크인, 체크아웃 날짜 받아오기
	@GetMapping("/getAvailable")
	@ResponseBody
	public List<RoomVO> getAvailable(Integer place_num, String reservation_checkin_date, String reservation_checkout_date) {
	//public List<RoomVO> getAvailable() {
		
		HashMap map = new HashMap();
		map.put("place_num", place_num);
		map.put("reservation_checkin_date", reservation_checkin_date);
		map.put("reservation_checkout_date", reservation_checkout_date);
		//model.addAttribute("room", dao.getAvailable(map));
		//System.out.println("동작함!!!!!!!");
		return dao.getAvailable(map);
	}


	



}
