package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.PlaceDAO;
import com.example.demo.vo.Board;
import com.example.demo.vo.Place;

import lombok.Setter;

@Service
@Setter
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private PlaceDAO pDAO;

	public int getNextPostNum() {
		return dao.getNextPostNum();
	}
	
	public void save(Board b) {
		dao.save(b);
	}
	

	public List<Board> findAll() {
		return dao.findAll();
	}
	
	public void insertBoard(Board b) {
		dao.insertBoard(b);
	}
	
	public void insertReview(Board b, int place_num) {
		Optional<Place> findPlace=pDAO.findById(place_num);
		b.setPlace(findPlace.get());
		dao.insertReview(b);
	}
	
	public Board findByPostNum(int post_num) {
		return dao.findByPostNum(post_num);
	}
	public List<Board> findByPlaceNum(int place_num) {
		return dao.findByPlaceNum(place_num);
	}
	
	public List<Board> goCategory(int board_num) {
		return dao.goCategory(board_num);
	}
		
	public Board detailPost(int board_num,int post_num) {
		return dao.detailPost(board_num,post_num);
	}

	public Board findById(int post_num) {
		return dao.findByPostNum(post_num);
	}

	public void updateBoard(Board b) {
		dao.update( b.getPost_title(), b.getPost_content(), b.getPost_num());
	}
	
	public void deleteBoard(int post_num) {
		dao.deleteById(post_num);
	}
	
	public int plusPostHit(int post_num) {
		return dao.plusPostHit(post_num);
	}
	

	public List<Board> findByPostTitle(int board_num, String post_keyword){
		return dao.findByPostTitle(board_num, post_keyword);
	}
	
	public List<Board> findByUserNum(int user_num){
		return dao.findByUserNum(user_num);
	}
	
	public List<Board> findByUserNumReview(int user_num){
		return dao.findByUserNumReview(user_num);
	}

}