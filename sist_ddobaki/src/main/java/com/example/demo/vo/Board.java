package com.example.demo.vo;


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="board")
public class Board {
	@Id
	private int post_num;
	//@Column(nullable = false) ==>not null

	@Column(nullable = false)
	private int board_num;
	
	@Column(columnDefinition = "varchar2(3000)",nullable = false)
	private String post_title;
	
	@Column(columnDefinition = "clob",nullable = false)
	private String post_content;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date post_regdate;
	
	@Column(nullable = false, columnDefinition = "number default 0")
	private int post_hit;
		
	//user_num과 다대일 fk		//findAll 찍을때 안나와서 jsonignore 삭제
	
	@ManyToOne
	@JoinColumn(name="user_num", insertable = true, updatable = true, nullable = false)
	private UserInfo userinfo;
	
	//place_num과 다대일/fk/null허용 
	@JsonIgnore
	//@SuppressWarnings(value={"all"})
	@ManyToOne
	@JoinColumn(name="place_num",insertable = true, updatable = true, nullable = true)
	private Place place;

	@JsonIgnore
	@Column(nullable = true)
	@OneToMany(mappedBy="board", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Reply> reply;

}