package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.DogForm;
import com.example.demo.service.DogService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Dog;

import lombok.Setter;

@Controller
@Setter
public class DogController {

	@Autowired
	private DogService dogService;

	@Autowired
	private UserInfoService userInfoService;

//	@ResponseBody
//	@GetMapping("/saveDog")
//	public Dog insert(Dog dog) {
//		
//		System.out.println(dog);
//		
//		dogService.save(dog);
//		return dog;
//	}

	// 강아지 추가
	@PostMapping("/saveDog")
	public String insert(DogForm dog) {
		Dog d = new Dog();
		d.setUserinfo(userInfoService.getUser(dog.getUser_num()));
		d.setDog_age(dog.getDog_age());
		d.setDog_desexed(dog.getDog_desexed());
		d.setDog_gender(dog.getDog_gender());
		d.setDog_name(dog.getDog_name());
		d.setDog_num(dogService.getNextDogNum());
		d.setDog_weight(dog.getDog_weight());
		dogService.save(d);
		return "redirect:/addDog";
	}

	// 강아지 삭제 
	@ResponseBody
	@GetMapping("/deleteDog")
	public void delete(int dog_num, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		dogService.deleteByUserDog(dog_num, (int) session.getAttribute("user_num"));
	}
//	
//	@ResponseBody
//	@GetMapping("/findDibs")
//	public List<Dibs> findDibs(int place_num){
//		List<Dibs> dlist = dibsService.findByPlaceNum(place_num);
//		return dlist;
//	}

}