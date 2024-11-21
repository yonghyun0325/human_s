package com.human.hms.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.SellerEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.AddressRepository;
import com.human.hms.repository.SellerRepository;
import com.human.hms.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	private AddressRepository a_repostiroy;
	private SellerRepository s_repository;
	// 메일 인증 관련 메일 전송 객체 의존 자동 주입 받기
	private JavaMailSenderImpl mailSender;

	@Override
	public UserEntity login(String userEmail, String userPw) {
		UserEntity entity = repository.login(userEmail, userPw);
		
		return entity;
	}
	
	@Override
	public UserEntity save(UserEntity vo) {
		return repository.save(vo);
	}


	@Override
	public AddressEntity a_save(AddressEntity a_vo, UserEntity vo) {
		a_vo.updateUserEntity(vo);
		return a_repostiroy.save(a_vo);
	}


	@Override
	public int sameIdcheck(String userEmail) {
		return repository.sameIdcheck(userEmail);
	}

	
	//이메일 인증코드 메시지
	@Override
	public String authEmail(String email) {
		//메일 인증 코드 6자리 생성하기: Math.random() (111111 <= r <100000)
		int authNumber = (int)(Math.random()*888889)+111111;
		String setFrom = "limdoh2022981038@gmail.com";//송신자의 메일주소
		String toMail = email;//수신자으 ㅣ메일주소
		String title = "회원가입 인증 이메일입니다";
		String content = "홈페이지를 방문해주셔서 감사합니다. <br><br>"
				+"인증번호: "+authNumber+"<br>"
				+"해당 인증번호를 인증번호 확인란에 입력해 주세요";
		mailSend(setFrom, toMail, title, content);
		System.out.println(email);
		return Integer.toString(authNumber);
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


	@Override
	public int sellerInfoCheck(String seIdNum) {
		return s_repository.sellerInfoCheck(seIdNum);
	}


	@Override
	public UserEntity equalsUser(String email) {
		return repository.equalsUser(email);
	}
	
	@Override
	public UserEntity findUserId(String name, String phone) {
		return repository.findUserId(name, phone);
	}


	@Override
	public UserEntity findUserPw(String email, String name) {
		// TODO Auto-generated method stub
		return repository.findUserPw(email , name);
	}


	@Override
	public SellerEntity s_save(SellerEntity s_vo, UserEntity vo) {
		s_vo.updateUserEntity(vo);
		return s_repository.save(s_vo);
	}
	
	
	//비밀번호 변경 전 세션 객체에 이메일이 있는지 확인 (있어야 비밀번호 변경 가능)
	@Override
	public UserEntity changeCheckEmail(String userEmail) {
		return repository.changeCheckEmail(userEmail);
	}

	//비밀번호 변경
	@Override
	public int changePassword(String userEmail, String userPw) {
		return repository.changePassword(userEmail, userPw);
	}



}
