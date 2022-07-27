package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Payment;
import com.example.demo.vo.Reservation;

import lombok.Setter;

@Controller
@Setter
public class ReservationController {

	@Autowired
	private ReservationService rs;

	@Autowired
	private UserInfoService us;

	@Autowired
	private PlaceService ps;

	@Autowired
	private RoomService roomS;
	
	@Autowired
	private PaymentService pms;

	// reservation 목록
	@GetMapping("reservation/listReservation")
	@ResponseBody
	public List<Reservation> list() {
		return rs.findAll();
	}

	// 예약한 reservation_num으로 가져오기
	@GetMapping("/getReservation/{reservation_num}") // url로 데이터를 전달하는 방식
	@ResponseBody
	public String getReservation(@PathVariable int reservation_num, Model model) { // 상태유지 하고 수정
		model.addAttribute("r", rs.getReservation(reservation_num));
		return "OK";
	}

	// 예약 insert할 때 user_num, place_num 받아오기
	@GetMapping("reservation/insertReservation/{place_num}")
	public String insert(Model model, @PathVariable Integer place_num, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user_id=userDetails.getUsername();
        int user_num=us.findByUser_id(user_id).getUser_num();
		model.addAttribute("us", us.getUser(user_num));
		model.addAttribute("ps", ps.getPlace(place_num));
		model.addAttribute("roomS", roomS.findByPlaceNum(place_num));
		model.addAttribute("reservation_num", rs.getNextReservationNum()); // 예약번호 자동
		model.addAttribute("pms",pms.getNextPaymentNum() );
		return "insertReservation";
	}

	
	 //insert 버튼 누른 후 예약정보 가져옴
	 @PostMapping("/saveReservation") 
	 public String save(ReservationDTO r, PaymentDTO pmt, Model model) {
		// Date date=new Date();
		 System.out.println("예약 정보: " + r ); //dto에서 reservation entity로 변환
		 Reservation rv = r.toReservationEntity();
		 Payment pm = pmt.toPaymentEntity();

		 rs.save(rv); 
		// System.out.println("db에 저장할 예약 객체 넘:"+rv.getReservation_num());
		// System.out.println("db에 저장할 결제 객체 날짜"+pm.getPayment_date());
		// System.out.println(pm.getPayment_num());
		 pm.setPayment_num(pms.getNextPaymentNum());
		 pm.setUserinfo(rv.getUserinfo());
		 pm.setReservation(rv);
		 pms.save(pm);
		 int user_num=pm.getUserinfo().getUser_num();
		 int reservation_num=rv.getReservation_num();
		 int payment_num=pm.getPayment_num();
		 
		 //System.out.println("결제: "+pmt);
		 //ModelAndView mav = new ModelAndView("listPayment");
		 //model.addAttribute("payment_num", pm.getPayment_num());
	
		 //mav.setView(new RedirectView("/payment/listPayment")); 
		 
		 return "redirect:/payment/listPayment/"+user_num+"/" + reservation_num + "/" + payment_num; 
		 
	 }
	


	// user_num 받아오기
	@GetMapping("/findByUserNum/{user_num}")
	@ResponseBody
	public List<Reservation> findByUserNum(@PathVariable int user_num) {
		return rs.findByUserNum(user_num);
	}

}
