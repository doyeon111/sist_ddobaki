package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlaceDAO;
import com.example.demo.dao.RoomDAO;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.Room;

import lombok.Setter;

@Service
@Setter
public class RoomService {
	
	@Autowired
	private RoomDAO roomdao;
	

	
	public List<Room> findAll() {
		return roomdao.findAll();
	}
	
	public Room getRoom(int room_num) {
		return roomdao.findById(room_num).get();
	}
	
	//place 번호를 찾아주는 메소드
	public List<Room> findByPlaceNum(Integer place_num) {
		return roomdao.findByPlaceNum(place_num);
	}
	
//	public List<Reservation> getAvailableRoom(int place_num, String reservation_checkin_date, String reservation_checkout_date) {
//		System.out.println("service 동작함!");
//		return roomdao.getAvailableRoom(place_num, reservation_checkin_date, reservation_checkout_date);
//	}
//	
//	public List<Room> findByPlaceAndRoomNum(int place_num, int room_num) {
//		return roomdao.findByPlaceAndRoomNum(place_num, room_num);
//	}

	
}