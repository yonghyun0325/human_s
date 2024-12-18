package com.human.hms.vo;



import lombok.Data;

@Data
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
	//addr entity
    public String addPost; //우편번호
	public String add1;//상세주소
	public String add2;
	public String addDate;
	public String addStatus;//히든으로 넣기 기본값 0
	//seller entity
	public String seIdNum;//사업자 등록번호
	public String seName; //상호명
	public String seBank;//은행명
	public String seBankNum;//계좌번호
	public String seBankName;//예금주	
}
