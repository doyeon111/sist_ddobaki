package com.example.demo.vo;


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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {
	@Id
	private int reservation_num;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_park;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_spa;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_meal_cnt;
	
	@Column(nullable = false) 
	private int reservation_price;
	 
	//@Column(name="reservation_checkin_date")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String reservation_checkin_date;
	
	
	//@Column(name="reservation_checkout_date")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private String reservation_checkout_date;
	
	@Column(columnDefinition = "number default 1", nullable = false)
	private int reservation_people_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false) 
	private int reservation_s_dog_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_m_dog_cnt;
	
	@Column(columnDefinition = "number default 0", nullable = false)
	private int reservation_l_dog_cnt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user_num", insertable = true, updatable = true)
	private UserInfo userinfo;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "place_num", insertable = true, updatable = true)
	private Place place;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "room_num", insertable = true, updatable = true)
	private Room room;
	
	@JsonIgnore
	@OneToOne(mappedBy = "reservation", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Payment payment; 
	
	
	//참조 받아야 하는 키
	//user_num, place_num, room_num
}

