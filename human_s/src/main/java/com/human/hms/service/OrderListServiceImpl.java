package com.human.hms.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.UnUserEntity;
import com.human.hms.repository.OrderListRepository;
import com.human.hms.repository.UnUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderListServiceImpl implements OrderListService {

	private OrderListRepository repository;
	private UnUserRepository un_repository;
	private JavaMailSenderImpl mailSender;
	
	//비회원 결제처리
	@Transactional
	@Override
	public OrderListEntity orderUnuser(UnUserEntity un_entity, OrderListEntity entity) {
		entity.updateOrIdx(orderIdx());
		
		OrderListEntity entity_save = repository.save(entity);
		
		un_entity.updateOrderList(entity_save);
		UnUserEntity un_entity_save = un_repository.save(un_entity);
		
		if(entity_save != null && un_entity_save != null) {
			authEmail(un_entity_save.getUnEmail(), entity_save.getOrIdx());
			return entity_save;
		}
		
		return null;
	}

	@Override
	public OrderListEntity orderuser(OrderListEntity entity) {
		entity.updateOrIdx(orderIdx());
		
		OrderListEntity entity_save = repository.save(entity);
		if(entity_save != null) {
			authEmail(entity.getUserEntity().getUserEmail(), entity_save.getOrIdx());
		}

		return entity_save;//회원결제처리
	}
	
	//주문번호 코드 생성하기
	public String orderIdx() {
		String orderNum = null;
		
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(today);
        
        //오늘날짜 주문 개수 확인하기
        int count = repository.countOrder(formattedDate);
        orderNum = formattedDate+count;
		
		return orderNum;
	}
	
	//이메일 전송
	@Override
	public String authEmail(String email, String authNumber) {
		String setFrom = "limdoh2022981038@gmail.com";//송신자의 메일주소
		String toMail = email;//수신자으 ㅣ메일주소
		String title = "고객님 결제한 주문번호입니다";
		String content = "결제해주셔서 감사합니다. <br><br>"
				+"주문번호: "+authNumber+"<br>"
				+"해당 주문번호로 비회원 로그인이 가능합니다";
		mailSend(setFrom, toMail, title, content);
		System.out.println(email);
		return authNumber;
	}
	private void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			//ture: multipart 형식의 메시지 전달
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			
		} catch (Exception e) {
			System.out.println("매일전송 중 예외발생");
		}
		
	}

}
