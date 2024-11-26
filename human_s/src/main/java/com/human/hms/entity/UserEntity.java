package com.human.hms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")  // 실제 데이터베이스의 테이블 이름
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 자동 증가 설정
    @Column(name = "user_idx")
    private int userIdx; //회원번호
    
    @Column(name = "user_email", nullable = false)
    private String userEmail; //아이디(이메일)
    
    @Column(name = "user_pw", nullable = false)
    private String userPw; //비밀번호
    
    @Column(name = "user_nick", nullable = false)
    private String userNick; //닉네임
    
    @Column(name = "user_name", nullable = false)
    private String userName; //이름
    
    @Column(name = "user_phone", nullable = false)
    private String userPhone; //전화번호
    
    @Column(name = "birth", nullable = false)
    private String birth; //생년월일
    
    @Column(name = "point", columnDefinition = "int default 0")
    private int point; //적립금
    
    @Column(name = "status", columnDefinition = "TINYINT DEFAULT 0")
    private int status; //탈퇴여부(0:신청안함, 1:신청함)
    
    @Column(name = "sellapply", columnDefinition = "TINYINT DEFAULT 0")
    private int sellapply; //판매자등록여부(0:등록안함, 1:등록신청함)
    
    @Column(name = "grade", columnDefinition = "TINYINT DEFAULT 1")
    private int grade; //등급(1:소비자, 2:판매자, 3:관리자)
    
    
    @Builder
    public UserEntity(String userEmail, String userPw, String userNick, 
    		String userName, String userPhone, String birth)  {
    	
    	this.userEmail = userEmail;
    	this.userPw = userPw;
    	this.userNick = userNick;
    	this.userName = userName;
    	this.userPhone = userPhone;
    	this.birth = birth;
    	
    	this.point = 0;
    	this.status = 0;
    	this.sellapply = 0;
    	this.grade = 1;
    }
    
    //userIdx필드에 값을 세팅해줄 수 있도록 명시적으로 변경 메소드 정의
    public void updateUserIdx(int userIdx) {
    	this.userIdx = userIdx;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail;
    }
    public void setStatus(int status) {
    	this.status = status;
    }
    
    public void setGrade(int grade) {
    	this.grade = grade;
    }

}
