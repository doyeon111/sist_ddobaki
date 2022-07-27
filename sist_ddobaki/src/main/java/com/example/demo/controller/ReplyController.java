package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.UserInfoDAO;
import com.example.demo.service.BoardService;
import com.example.demo.service.ReplyService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Reply;

import lombok.Setter;

@Controller
@Setter
public class ReplyController {
	
	@Autowired
	private ReplyService rps;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private UserInfoService uis;
	
	@Autowired
	private UserInfoDAO uDAO;
	
	@RequestMapping(value="/board/insertReplyOK",method=RequestMethod.POST)
    public String insertReplyOK(int board_num, int post_num, int user_num, Reply rp){
       System.out.println("댓글ok컨트롤러 옴");
       System.out.println("해당 댓글의 게시글 넘버 :"+post_num);
       System.out.println("해당 댓글의 작성자 넘버 :"+user_num);
       int rp_num=rps.getNextReplyNum();
       rp.setReply_num(rp_num);
       rp.setReply_group(rp_num);
       System.out.println(rp.toString());
       rps.insertReply(rp,user_num,post_num);
       return "redirect:/detailPost/"+board_num+"/"+post_num;
   }
	
	@RequestMapping(value="/board/insertReReply/{reply_num}", method=RequestMethod.GET)
	public void insertReReply(@PathVariable int reply_num, Model model) {
		System.out.println("대댓글 입력 컨트롤러 옴!!!");
		model.addAttribute("reply_num", reply_num);
		System.out.println("부모댓글의 넘버:"+reply_num);
	}
	
	@RequestMapping(value="/board/insertReReplyOK/{reply_num}",method=RequestMethod.POST)
    public String insertReReplyOK(Reply rp, int user_num, int post_num, @PathVariable int reply_num, int board_num, Model model){
       System.out.println("대댓글ok컨트롤러 옴");
       System.out.println("대댓글ok컨트롤러로 온 부모댓글의 넘버:"+reply_num);
       rp.setReply_num(rps.getNextReplyNum());
       rp.setBoard(bs.findById(post_num));
       rp.setUserinfo(uis.getUser(user_num));
       rp.setRef_reply_num(reply_num);
       System.out.println("대댓글 작성자 이름:"+rp.getUserinfo().getUser_nick());
       System.out.println("해당 게시글 번호:"+rp.getBoard().getPost_num());
       rp.setReply_level(1);
       rps.insertReReply(rp,user_num,post_num,reply_num);
       return "redirect:/detailPost/"+board_num+"/"+post_num;
   }
	
	@RequestMapping(value="/board/deleteReply/{reply_num}", method=RequestMethod.GET)
	public String deleteReply(@PathVariable int reply_num) {
		rps.deleteReplyOneByOne(reply_num);
		return "redirect:/firstListBoard";
	}
	
}