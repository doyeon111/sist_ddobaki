package com.example.demo.dao;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.PlaceImage;


@Repository
public interface PlaceImageDAO extends JpaRepository<PlaceImage, Integer>{
	
	@Query("select pi from PlaceImage pi where place_num=:place_num")
	public List<PlaceImage> findByPlaceNum(@Param("place_num") int place_num);
	
//	List<PlaceImage> findByPlaceNum(int place_num);
	
}