package com.example.demo.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.PlaceSearchCondition;
import com.example.demo.service.BoardService;
import com.example.demo.service.DibsService;
import com.example.demo.service.PlaceImageService;
//import com.example.demo.service.PlaceImageService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Place;

@RestController
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private PlaceImageService placeImgService;
	
	@Autowired
	private UserInfoService us;
	
	@Autowired
	private DibsService dibsService;
	
	
//	@ResponseBody
//	@GetMapping("/getCondition") 
//	public String test(PlaceSearchCondition param){ 
//		System.out.println(param);
//		return param.toString();
//		
//	}
//	
//	@ResponseBody
//	@GetMapping("/listPlace")
//	public List<Place> listPlace(){
//		PlaceSearchCondition condition = new PlaceSearchCondition();
//
//		condition.setPlace_meal(1);
//		condition.setPlace_park(1);
//		condition.setPlace_spa(1);
//		
//		
//		List<Place> plist = placeService.findByCondition(condition);
//		
//		return plist;
//	}
//	
//	@RequestMapping(value="/placeMain", method = RequestMethod.GET) 
//	public String listBycondition(PlaceSearchCondition condition, Model model){	
//		
//		model.addAttribute("list", placeService.findByCondition(condition));
//		System.out.println(placeService.findByCondition(condition).toString());
//		return "place_main";
//	} 

	// place_main html 주소 설정(template에다가 html 파일 넣기!)
	@GetMapping("/place")
	public ModelAndView placePageView() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("place_main");

		return modelAndView;
	}

	// 검색 조건에 따른 place list 반환
	@RequestMapping(value = "/placeMain", method = RequestMethod.GET)
	public List<Place> listBycondition(PlaceSearchCondition condition) {
		System.out.println(condition);

		List<Place> plist = placeService.findByCondition(condition);

		return plist;

	}

	@GetMapping("/place/detailPlace/{place_num}")
	public ModelAndView detailPlace(@PathVariable int place_num, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
//		int user_num = 4;
//		
//		
//		// 세션 무한 유지
//		session.setMaxInactiveInterval(-1);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user_id=userDetails.getUsername();
        int user_num=us.findByUser_id(user_id).getUser_num();
		
//        세션 값 설정
		session.setAttribute("user_num", user_num);
		
//		session.getAttribute("user_num");
		
		ModelAndView mav = new ModelAndView("detailPlace");
		placeService.updateHit(place_num);
		mav.addObject("review", bs.findByPlaceNum(place_num));
		mav.addObject("place", placeService.getPlace(place_num));
		mav.addObject("piList", placeImgService.getPlaceImage(place_num));
		return mav;
	}
	
	@GetMapping("/listPlace")
	@ResponseBody
	public List<Place> list() {
		return placeService.findAll();
	}
	
	@GetMapping("/getPlace")
	@ResponseBody
	public Place getPlace(@RequestParam int place_num) {
		return placeService.getPlace(place_num);
	}
	
	@GetMapping("/listReviewPlace")
	@ResponseBody
	public List<Place> findByRandT(@RequestParam int place_region_num, int place_type_num){
		//System.out.println("place컨트롤러 옴");
		//System.out.println("매개변수로 온 region_num"+place_region_num);
		//System.out.println("매개변수로 온 type_num"+place_type_num);
		List<Place> list=placeService.placeNameList(place_region_num, place_type_num);
		//model.addAttribute("place_list", list);
		//System.out.println("출력될 리스트 길이:"+list.size());
		return list;
	}
	
	

}
