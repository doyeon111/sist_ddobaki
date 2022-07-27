package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.RoomVO;


@Repository
public class Room2DAO {
//	public List<RoomVO> findAll() {
//		return DBManager.findAll();
//	}
//	
	//체크인, 체크아웃 누를 시 ajax와 통신하기 위한 메소드(룸번호, 룸타입번호, 룸이름 출력)
	public List<RoomVO> getAvailable(Map map) {
		return DBManager.getAvailable(map);
	}
}
