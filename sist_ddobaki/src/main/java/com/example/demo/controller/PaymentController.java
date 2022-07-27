package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.ReservationDTO;
import com.example.demo.service.PaymentService;
import com.example.demo.service.PlaceService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.Payment;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@RestController
@Setter
public class PaymentController {
	
	@Autowired
	private UserInfoService us;
	
	@Autowired
	private PaymentService ps;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private ReservationService rs;
	
	//결제 전체 리스트 목록
	@GetMapping("payment/listPayment/{user_num}/{reservation_num}/{payment_num}")
	public ModelAndView list( @PathVariable int user_num, @PathVariable int payment_num, @PathVariable int reservation_num, Model model) {
		ModelAndView mav = new ModelAndView("listPayment");
		System.out.println("결제list컨트롤러 ");
		model.addAttribute("us", us.getUser(user_num));
		//model.addAttribute("rs", rs.getReservation(reservation_num));
		//model.addAttribute("rs", rs.getReservation(reservation_num));
		model.addAttribute("ps", ps.getPayment(payment_num));
		int price= ps.getPayment(payment_num).getReservation().getReservation_price();
		String name= ps.getPayment(payment_num).getReservation().getPlace().getPlace_name();
		
		model.addAttribute("price", price);
		model.addAttribute("name", name);
		//System.out.println(ps.getPayment(payment_num));
		return mav; // ps.getPayment(payment_num);
	}
	
	//결제 insert할 때 user_num, reservation_num 받아오기
	@GetMapping("insertPayment/{user_num}/{reservation_num}")
	@ResponseBody
	public String insert(Model model, @PathVariable int user_num, @PathVariable int reservation_num) {
		model.addAttribute("us", us.getUser(user_num));
		model.addAttribute("payment_num", ps.getNextPaymentNum()); //예약번호 자동
		//System.out.println(ps.getNextPaymentNum());
		return "insertPayment";
	}
		
		
	//insert 버튼 누른 후 결제정보
	@PostMapping("/savePayment")
	public ModelAndView save(PaymentDTO pmt) {
		System.out.println("결제 정보: " + pmt);
		//dto에서 reservation entity로 변환
		Payment p = pmt.toPaymentEntity();
		ps.save(p);
		ModelAndView mav = new ModelAndView();
			
		mav.setView(new RedirectView("/savePayment"));
		return mav;
	}
	
	/*
	 * //@ResponseBody
	 * 
	 * @GetMapping("/getPayment") public String getPayment(@PathVariable int
	 * user_num, @PathVariable int reservation_num, @PathVariable int payment_num,
	 * Model model) { //Payment payment = ps.getPayment(payment_num);
	 * model.addAttribute("us", us.getUser(user_num)); model.addAttribute("rs",
	 * rs.getReservation(reservation_num)); model.addAttribute("pm",
	 * ps.getPayment(payment_num)); return "listPayment"; }
	 */
	

	
	public String getReservation(@PathVariable int reservation_num, Model model) { // 상태유지 하고 수정
		model.addAttribute("r", rs.getReservation(reservation_num));
		return "OK";
	}
	
}
