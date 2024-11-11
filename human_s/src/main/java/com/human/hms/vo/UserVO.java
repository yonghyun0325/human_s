package com.human.hms.vo;

import com.human.hms.entity.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
 	private int userIdx; //회원번호
    private String userEmail; //아이디(이메일)
    private String userPw; //비밀번호
    private String userNick; //닉네임
    private String userName; //이름
    private String userPhone; //전화번호
    private String birth; //생년월일
    private int point; //적립금
    private int status; //탈퇴여부(0:신청안함, 1:신청함)
    private int sellapply; //판매자등록여부(0:등록안함, 1:등록신청함))
    private int grade; //등급(1:소비자, 2:판매자, 3:관리자)
    
    // UserEntity를 UserVO로 변환하는 정적 메서드
    public static UserVO fromEntity(UserEntity userEntity) {
        return UserVO.builder()
                .userIdx(userEntity.getUserIdx())
                .userEmail(userEntity.getUserEmail())
                .userPw(userEntity.getUserPw())
                .userNick(userEntity.getUserNick())
                .userName(userEntity.getUserName())
                .userPhone(userEntity.getUserPhone())
                .birth(userEntity.getBirth())
                .point(userEntity.getPoint())
                .status(userEntity.getStatus())
                .sellapply(userEntity.getSellapply())
                .grade(userEntity.getGrade())
                .build();
    }
	  
}
