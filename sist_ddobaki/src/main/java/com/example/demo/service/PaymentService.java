package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentDAO;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.vo.Payment;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.UserInfo;

import lombok.Setter;

@Service
@Setter
public class PaymentService {
	@Autowired
	private PaymentDAO dao;
	
	public List<Payment> findAll() {
		return dao.findAll();
	}
	
	//결제번호 자동으로 받아오기
	public int getNextPaymentNum() {
		return dao.getNextPaymentNum();
	}
	
	//결제 insert
	public void save(Payment p) {
		//p.setPayment_date();
		dao.insert(p);
	}
	
	public Payment getPayment(int payment_num) {
		return dao.findById(payment_num).get();
	}
	
}
