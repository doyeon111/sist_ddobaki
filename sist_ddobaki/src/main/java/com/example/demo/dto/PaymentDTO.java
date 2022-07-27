package com.example.demo.dto;

import java.util.Date;

import com.example.demo.vo.Payment;
import com.example.demo.vo.Place;
import com.example.demo.vo.Reservation;
import com.example.demo.vo.Room;
import com.example.demo.vo.UserInfo;

import lombok.Data;

@Data
public class PaymentDTO {
	private int payment_num;
	private Date payment_date;
	private int reservation_num;
	private int user_num;
	
	
	//예약 insert 될 때, dto인 toReservationEntity 메소드로 다 담아오기
		public Payment toPaymentEntity() {
			Payment pob = new Payment();
			Reservation ob = new Reservation();
			
			pob.setPayment_num(payment_num);
			pob.setPayment_date(payment_date);
			pob.setReservation(null);
		
			UserInfo info = new UserInfo();
			info.setUser_num(user_num);
			pob.setUserinfo(info);
			
			return pob;

			
		}
}
