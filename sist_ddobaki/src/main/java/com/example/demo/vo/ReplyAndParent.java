package com.example.demo.vo;

import lombok.Data;
@Data
public class ReplyAndParent {

	private int	reply_num;

	private Board board;
	
	private UserInfo userinfo;	
	
	private int	reply_group;
	
	private int	reply_level;
	
	private int	reply_step;
	
	private String reply_content;
	
	private int isDeleted;
	
	private int ref_reply_num;
	
	private String parent_nick;

}