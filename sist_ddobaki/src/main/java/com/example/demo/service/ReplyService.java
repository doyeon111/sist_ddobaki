package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.ReplyDAO;
import com.example.demo.dao.UserInfoDAO;
import com.example.demo.vo.Board;
import com.example.demo.vo.Reply;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Service
@Setter
public class ReplyService {
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private UserInfoDAO uDAO;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private BoardDAO bDAO;
	
	public int getNextReplyNum() {
		return dao.getNextReplyNum();
	}
	
	public int getNextReplyStepByReplyGroup(int reply_group) {
		return dao.getNextReplyStepByReplyGroup(reply_group);
	}

	public List<Reply> findByPostNum(int post_num) {
		return dao.findByPostNum(post_num);
	}
	
	public void insertReply(Reply rp, int user_num, int post_num) {
		Optional<UserInfo> findUser=uDAO.findById(user_num);
		Optional<Board> findBoard=bDAO.findById(post_num);
		rp.setBoard(findBoard.get());
		rp.setUserinfo(findUser.get());
		dao.insertReply(rp);
	}
	
	public void insertReReply(Reply rp, int user_num, int post_num, int reply_num) {
		Optional<UserInfo> findUser=uDAO.findById(user_num);
		Optional<Board> findBoard=bDAO.findById(post_num);
		Optional<Reply> findReply=dao.findById(reply_num);
		rp.setBoard(findBoard.get());
		rp.setUserinfo(findUser.get());
		rp.setReply_group(findReply.get().getReply_group());
		rp.setRef_reply_num(reply_num);
		rp.setReply_step(getNextReplyStepByReplyGroup(findReply.get().getReply_group()));
		dao.insertReReply(rp);
	}
	
	public void deleteByPostNum(int post_num) {
		dao.deleteReplyInMyPost(post_num);
	}
	
	public void deleteReplyOneByOne(int reply_num) {
		dao.deleteMyReplyOneByOne(reply_num);
	}
	
	public int getUserNumByReplyNum(int reply_num) {
		return dao.getUserNumByReplyNum(reply_num);
	}
}