package com.example.demo.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Reply;

@Repository
public interface ReplyDAO extends JpaRepository<Reply, Integer> {
	
	//게시글 하나씩 클릭할 때마다 해당 게시글에 딸려있는 댓글들 모두 뿌리기
	@Query(value="select * from Reply where post_num=:post_num order by reply_group, reply_step asc", nativeQuery=true)
	public List<Reply> findByPostNum(int post_num);
	
	//user_num이 지금 로그인한 사용자면 자기가 쓴 댓글 개별로 삭제 가능하게 하기
	//삭제버튼은 지금 로그인한 사용자만 볼 수 있음 따라서 user_num 불필요
	@Modifying
	@Query(value="update Reply set reply_content='삭제된 댓글입니다.', is_deleted=1 where reply_num=:reply_num", nativeQuery=true)
	@Transactional
	public void deleteMyReplyOneByOne(int reply_num);
	
	//세션에 저장되어있는 user_num한테만 삭제버튼 보이게 할건지
	//게시글 작성 사용자가 게시글을 지운다고 하면 그 사용자가 쓴 댓글 아니어도 그 게시글에 딸려있는 모든 댓글 다 삭제 처리
	@Modifying
	@Query(value="delete Reply where post_num=:post_num", nativeQuery=true)
	@Transactional
	public void deleteReplyInMyPost(int post_num);
	
	//댓글 등록버튼 하나씩 누를 때마다 db에 insert되기 전에 댓글 넘버 부여
	@Query(value="select nvl(max(reply_num),0)+1 from Reply", nativeQuery=true)
	public int getNextReplyNum();
	
	//insert댓글(매개변수는 지금 로그인한 사용자의 user_num이랑 지금 클릭한 게시글의 post_num)
	@Modifying
	@Query(value="insert into Reply rp(rp.reply_num, rp.post_num, rp.user_num, rp.reply_group, rp.reply_level, rp.reply_step, rp.reply_content, rp.is_deleted, rp.ref_reply_num) "+ 
			"values(:#{#rp.reply_num}, :#{#rp.board.post_num}, :#{#rp.userinfo.user_num}, :#{#rp.reply_num}, 0, 0, :#{#rp.reply_content}, 0, 0)", nativeQuery=true)
	@Transactional
	public void insertReply(@Param("rp") Reply rp);
	
	//--------------------------------------------------대댓글-----------------------------------------
	//대댓글 쓰고 나서 등록 버튼 누름과 동시에 동작하는 쿼리
	//해당 댓글 그룹 내에서 순서가 자동으로 부여됨
	@Query(value="select nvl(max(reply_step),0)+1 from Reply where reply_group=:reply_group", nativeQuery=true)
	public int getNextReplyStepByReplyGroup(int reply_group);
	
	//insert대댓글
	//부모 댓글의 답글쓰기 누르고 내용 작성하고 등록버튼 클릭과 동시에 동작하는 쿼리
	@Modifying
	@Query(value="insert into Reply rp(rp.reply_num, rp.post_num, rp.user_num, rp.reply_group, rp.reply_level, rp.reply_step, rp.reply_content, rp.is_deleted, rp.ref_reply_num) "+ 
			"values(:#{#rp.reply_num}, :#{#rp.board.post_num}, :#{#rp.userinfo.user_num}, :#{#rp.reply_group}, 1, :#{#rp.reply_step}, :#{#rp.reply_content}, 0, :#{#rp.ref_reply_num})", nativeQuery=true)
	@Transactional
	public void insertReReply(@Param("rp") Reply rp);
	
	@Query(value="select nvl(user_num,0) from reply where reply_num=:reply_num", nativeQuery=true)
	public Integer getUserNumByReplyNum(int reply_num);
	
}