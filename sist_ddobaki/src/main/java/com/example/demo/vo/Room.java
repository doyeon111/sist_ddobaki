package com.example.demo.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {
	
	@Id
	//@Column(name="room_num")
	private int room_num;

	//@Column(name="room_type_num")
	private int room_type_num;

	
	//@Column(name="room_price")
	private int room_price;
	
	//@Column(name="room_name")
	private String room_name;

	@ManyToOne
	@JoinColumn(name="place_num", insertable=true, updatable=true)
	private Place place;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<Reservation> reservation;
	
	//참조받아야 하는 키
	//place_num
}
