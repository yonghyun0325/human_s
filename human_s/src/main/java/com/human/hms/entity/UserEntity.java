package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "user")  // 실제 데이터베이스의 테이블 이름
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
    
    public UserEntity() {
    	this.point = 0;
    	this.status = 0;
    	this.sellapply = 0;
    	this.grade = 1;
    }
}
