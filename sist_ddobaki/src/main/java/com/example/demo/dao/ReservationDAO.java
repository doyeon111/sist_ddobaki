package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Reservation;


@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
	
	
	
	//예약된 방목록을 반환
//	public List<Reservation> findByReservationCheckinDateBetween(String reservation_checkin_date, String reservation_checkout_date);
	


	//예약번호를 자동으로 생성해주기 위한 메소드
	@Query("select nvl(max(reservation_num), 0) + 1 from Reservation") //쿼리문 직접 작성 => 쿼리문에 들어간 테이블 이름은 entity에 들어간 클래스 이름과 일치해야함!!!
	public int getNextReservationNum();
	
	
	//예약 insert 쿼리
	@Modifying
	@Query(value = "insert into Reservation r(r.reservation_num, r.user_num, r.place_num, r.room_num, r.reservation_park, "
			+ "r.reservation_spa, r.reservation_meal_cnt, r.reservation_price, r.reservation_checkin_date, "
			+ "r.reservation_checkout_date, r.reservation_people_cnt, r.reservation_s_dog_cnt, r.reservation_m_dog_cnt, r.reservation_l_dog_cnt) "
			+ "values(:#{#r.reservation_num}, :#{#r.userinfo.user_num}, :#{#r.place.place_num}, :#{#r.room.room_num}, "
			+ ":#{#r.reservation_park}, :#{#r.reservation_spa}, :#{#r.reservation_meal_cnt}, :#{#r.reservation_price}, :#{#r.reservation_checkin_date} "
			+ ":#{#r.reservation_checkout_date}, :#{#r.reservation_people_cnt}, :#{#r.reservation_s_dog_cnt}, :#{#r.reservation_m_dog_cnt}, :#{#r.reservation_l_dog_cnt}),", nativeQuery = true)
	@Transactional 
	public void insert(@Param("r") Reservation r); 
 
	
	//userinfo에서 user에 대한 정보 가져오기
	@Query("select r from Reservation r where user_num=:user_num") 
	public List<Reservation> findByUserNum(@Param("user_num") int user_num);
	
	
	

	
}
