package com.example.demo.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="boardimage")
public class BoardImage {
	@Id
	private int board_image_num;
	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name="post_num", insertable=true, updatable=true, nullable = false)
//	private Board board;
	
	@Column(length = 4000, nullable = false)
	private String board_image_file;
	
	@Column(length = 4000, nullable = false)
	private String saved_name; //변형된 파일이름
	
	@Column(length = 4000, nullable = false)
    private String file_path; // 저장된 파일경로
	
	@Column
	private String contentType;
	
//	
//	private int size;
}