package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Reservation;
import com.example.demo.vo.Room;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
	
	
	@Query("select roomS from Room roomS where place_num=:place_num")
	public List<Room> findByPlaceNum(@Param("place_num") int place_num);
	

	
	
	
	
}
