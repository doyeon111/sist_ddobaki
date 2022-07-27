package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.Payment;
import com.example.demo.vo.Reservation;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer> {

	//결제번호를 자동으로 생성해주기 위한 메소드
	@Query("select nvl(max(payment_num), 0) + 1 from Payment")
	public int getNextPaymentNum();
	
	//예약 insert 쿼리
	@Modifying
	@Query(value = "insert into Payment pm(pm.payment_num, pm.payment_date, pm.reservation_num, pm.user_num) "
			+ "values(:#{#pm.payment_num}, sysdate, :#{#pm.reservation.reservation_num}, :#{#pm.userinfo.user_num})", nativeQuery = true)
	@Transactional 
	public void insert(@Param("pm") Payment pm); 
	
}
