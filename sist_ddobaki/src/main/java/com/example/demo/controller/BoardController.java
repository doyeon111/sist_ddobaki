package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BoardService;
import com.example.demo.vo.Board;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;

	
	@GetMapping("/listBoard")
	public void listBoard(Model model) {
		model.addAttribute("list", bs.findAll());
	}
	
	//카테고리 하나 눌렀을때 그 페이지로
	@GetMapping("/category/{board_num}")
	public ModelAndView goCategory(@PathVariable int board_num,Model model) {
		ModelAndView mav = new ModelAndView("category");
		model.addAttribute("boardCategory", bs.goCategory(board_num));
		return mav;
	}
	
//	@GetMapping("/insertBoard")
//	public void insert(Model model) {
//		//겟방식으로 이게 오면 새로운 주문번호까지 상태유지함 !!! 우리가 이걸 써먹어야함~!!!!!!!!!!!!꼭 기억하고 있기
//		model.addAttribute("post_num", bs.getNextPostNum());
//	}
	
	//insertBoard.html에서 다 쓰면 여기로 와서 insert가 되는 것임 (폼태그 방식이 post니까)
	@PostMapping("/insertBoard")
	@ResponseBody
	public String insertOK(Board b) {
		b.setPost_num(bs.getNextPostNum());
		bs.insert(b);
		return "OK";
	}
	
	//4개 하위 게시판 중 하나로 가면 이 컨트롤러 동작해서 post_num 부여해줌
	@PostMapping("/goToWriteBoard/{board_num}")
	public String writeBoardProcess(@PathVariable int board_num,Board b) {
		b.setBoard_num(board_num);
		b.setPost_num(bs.getNextPostNum());
		return "redirect:/insertBoard/" + b.getBoard_num()+"/"+b.getPost_num();
	}
	
	//insertBoard.html에서 다 쓰면 여기로 와서 insert가 되는 것임 (폼태그 방식이 post니까)
	@PostMapping("/insertBoard/{board_num}/{post_num}")
	@ResponseBody
	public String insertOK(@PathVariable int board_num,@PathVariable int post_num,Board b) {
		b.setBoard_num(board_num);
		b.setPost_num(post_num);
		b.setPost_num(bs.getNextPostNum());
		bs.insert(b);
		return "OK";
	}

	//상세보기 눌렀을때 board_num도 함께 가도록
	@GetMapping("/detailPost/{board_num}/{post_num}")
	public ModelAndView detailPost(@PathVariable int board_num,@PathVariable int post_num, Model model) {
		//System.out.println("detailPost의 board_num:"+board_num);
		//System.out.println("detailPost의 post_num:"+post_num);
		ModelAndView mav = new ModelAndView("detailPost");
		model.addAttribute("b",bs.detailPost(board_num, post_num));
		return mav;
	}
//	
//	 @PostMapping("")
//	    public String setArticle(Article article, Model model) {
//	    	article.setRegisterDate(LocalDateTime.now());
//	    	System.out.println(article);
//	    	articleRepository.save(article);
//	    	return "redirect:/article/" + article.getId();
//	    }
//	    
//	    @GetMapping("/{id}")
//	    public String getArticle(Model model, @PathVariable Long id) {
//	    	Article article = articleRepository.findById(id).get();
//	    	model.addAttribute("article", article);
//	    	return "article/detail";
//	    }
//	    
//	    @GetMapping("")
//	    public String getArticleList(Model model) {
//	    	List<Article> articleList = articleRepository.findAll();
//	    	model.addAttribute("articleList", articleList);
//	    	articleList.forEach(System.out::println);
//	    	return "article/list";
//	    }
//	    
//	    @GetMapping("/update/{id}")
//	    public String getArticleUpdate(Model model, @PathVariable Long id) {
//	    	Article article = articleRepository.findById(id).get();
//	    	System.out.println(article);
//	    	model.addAttribute("article", article);
//	    	return "article/update";
//	    }
//	    
//	    @PostMapping("/update/{id}")
//	    public String setArticleUpdate(Model model, @PathVariable Long id, Article updatedArticle) {
//	    	Article article = articleRepository.findById(id).get();
//	    	article.setTitle(updatedArticle.getTitle());
//	    	article.setContent(updatedArticle.getContent());
//	    	article.setUpdateDate(LocalDateTime.now());
//	    	articleRepository.save(article);
//	    	return "redirect:/article/" + article.getId();
//	    	
//	    }
//	    
	
}