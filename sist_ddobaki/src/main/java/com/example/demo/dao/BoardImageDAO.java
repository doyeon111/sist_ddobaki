package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BoardImage;
@Repository
public interface BoardImageDAO extends JpaRepository<BoardImage, Integer> {
	
	//게시글 쓸 때 이미지도 추가할 때마다 게시글 이미지 넘버 자동 부여
	@Query(value="select nvl(max(board_image_num),0)+1 from BoardImage", nativeQuery=true) 
	public int getNextBoardImageNum();
	
	@Modifying
	@Query(value="insert into BoardImage bi(bi.board_image_num, bi.board_image_file, bi.post_num) values(:#{#bi.board_image_num}, :#{#bi.board_image_file}, :#{#bi.board.post_num})", nativeQuery=true)
	@Transactional
	public int insert(@Param("bi") BoardImage bi);
	
//	//게시글 하나 클릭했을 떄 그 게시글 안에 있는 이미지들 모두 딸려와서 뿌려주기
//	//BoardImage와 Board 조인하여 post_num으로 이미지들 가져오기
//	@Query("select * from BoardImage where post_num=:post_num")
//	public List<BoardImage> findByPostNum(int post_num);
	
//	//마이페이지에서 내가 쓴 리뷰에서 리뷰게시글(board_num=3)에 이미지가 있다면 그 글 이미지들 그룹에서 첫번째 아이만 뿌리기 
//	@Query("select * from BoardImage where post_num=:post_num limit 1")
//	public List<BoardImage> findMyReviewImage(int post_num);
//	//만약에 쿼리문을 읽지 못하면 다 가져와서 클라이언트 단에서 한개만 뽑도록 처리
//	
	
//	//board_image_num으로 게시글 등록할 때나 수정할 때 미리보기 옆에 x클릭할 때마다 하나씩 삭제
//	@Modifying
//	@Query("delete BoardImage where post_num=:post_num and board_image_num=:board_image_num")
//	@Transactional
//	public void delete(@Param("bi") BoardImage bi);
//	
//	//post_num으로 게시글 삭제할 때 해당 게시글에 있는 이미지들 전체 삭제
//	@Modifying
//	@Query("delete BoardImage where post_num=:post_num")
//	@Transactional
//	public void deleteByPostNum(@Param("bi") BoardImage bi);

}