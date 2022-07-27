package com.example.demo.dto;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.vo.Place;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.Room;
import com.example.demo.vo.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;


@Data
public class ReservationDTO {
	private int reservation_num;
	private int reservation_park;
	private int reservation_spa;
	private int reservation_meal_cnt;
	private int reservation_price;
	private String reservation_checkin_date;
	private String reservation_checkout_date;
	private int reservation_people_cnt;
	private int reservation_s_dog_cnt;
	private int reservation_m_dog_cnt;
	private int reservation_l_dog_cnt;
	private int user_num;
	private int place_num;
	private int room_num;
	
	
	//예약 insert 될 때, dto인 toReservationEntity 메소드로 다 담아오기
	public Reservation toReservationEntity() {
		Reservation ob = new Reservation();
		ob.setReservation_num(reservation_num);
		ob.setReservation_park(reservation_park);
		ob.setReservation_spa(reservation_spa);
		ob.setReservation_meal_cnt(reservation_meal_cnt);
		ob.setReservation_price(reservation_price);
		ob.setReservation_checkin_date(reservation_checkin_date);
		ob.setReservation_checkout_date(reservation_checkout_date);
		ob.setReservation_people_cnt(reservation_people_cnt);
		ob.setReservation_s_dog_cnt(reservation_s_dog_cnt);
		ob.setReservation_m_dog_cnt(reservation_m_dog_cnt);
		ob.setReservation_l_dog_cnt(reservation_l_dog_cnt);
		UserInfo info = new UserInfo();
		info.setUser_num(user_num);
		ob.setUserinfo(info);
		
		Place place = new Place();
		place.setPlace_num(place_num);
		ob.setPlace(place);
		
		Room room = new Room();
		room.setRoom_num(room_num);
		ob.setRoom(room);
		return ob;
		
	}

}
