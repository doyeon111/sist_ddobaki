package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Board;

@Repository
@EnableJpaRepositories
public interface BoardDAO extends JpaRepository<Board, Integer> {
	
	@Query(value="select * from Board order by post_num desc",nativeQuery = true)
	public List<Board> findAll();

	// 게시글 번호 자동부여
	@Query("select nvl(max(post_num),0)+1 from Board")
	public int getNextPostNum();

	// 조회수 증가 detail화면으로 넘어가면
	@Modifying
	@Query("update Board set post_hit=post_hit+1 where post_num=:post_num")
	@Transactional
	public int plusPostHit(int post_num);

	//제목으로 게시글 찾기		
	@Query(value="select * from Board where board_num=:board_num and post_title like '%'||:post_keyword||'%' order by post_num desc" ,nativeQuery = true)
	public List<Board> findByPostTitle(int board_num, String post_keyword);

	// 리뷰게시판출력 //말머리 컨디션 받았을떄(region_num, place_type_num 2개 placeDAO에서 먼저 받기)
	@Query(value = "select * from Board where place_num=:place_num order by post_num desc", nativeQuery = true)
	public List<Board> findByPlaceNum(int place_num);

	// 유저 번호로 쓴 글 출력(내가 쓴 글 출력)
	@Query(value = "select * from Board where user_num =:user_num and board_num !=3 order by post_regdate desc", nativeQuery = true)
	public List<Board> findByUserNum(int user_num);

	// 내가 쓴 리뷰
	@Query(value = "select * from Board where user_num =:user_num and board_num=3 order by post_regdate desc", nativeQuery = true)
	public List<Board> findByUserNumReview(int user_num);

	// 오늘의 추천게시물 +
	@Query(value = "select * from Board where post_num=:post_num", nativeQuery = true)
	public Board findByPostNum(int post_num);

	// 게시판 카테고리 딱 눌렀을떄 이동
	@Query(value = "select * from Board where board_num=:board_num order by post_num desc", nativeQuery = true)
	public List<Board> goCategory(int board_num);

	// 게시판+게시물 경로 //게시물 제목 눌러서 상세보기(상세보기할때는 view_BoardDAO에서 먼저)
	@Query(value = "select * from Board where board_num=:board_num and post_num=:post_num", nativeQuery = true)
	public Board detailPost(int board_num, int post_num);

	// 게시글 등록 
	@Modifying
	@Query(value = "insert into Board b(b.board_num,b.post_num, b.user_num, b.post_title, b.post_content, b.post_regdate,"
			+ "b.post_hit,b.place_num) values(:#{#b.board_num},:#{#b.post_num},:#{#b.userinfo.user_num},:#{#b.post_title},"
			+ ":#{#b.post_content},sysdate,0,null)", nativeQuery = true)
	@Transactional
	public void insertBoard(@Param("b") Board b);

	// 리뷰 게시글 등록 (region_num, place_type_num 2개 placeDAO에서 먼저)
	@Modifying
	@Query(value = "insert into Board b(b.board_num,b.post_num, b.user_num, b.post_title, b.post_content, b.post_regdate,"
			+ " b.post_hit,b.place_num) values(:#{#b.board_num},:#{#b.post_num},:#{#b.userinfo.user_num},:#{#b.post_title},"
			+ ":#{#b.post_content},sysdate,0,:#{#b.place.place_num})", nativeQuery = true)
	@Transactional
	public void insertReview(@Param("b") Board b);

	// 게시글 수정
	@Modifying
	@Query(value = "update Board set post_title=:#{#post_title}, post_content=:#{#post_content}"
			+ " where post_num=:post_num", nativeQuery = true)
	@Transactional
	public void update(@Param("post_title") String post_title, @Param("post_content") String post_content,
			@Param("post_num") int post_num);

}