package com.human.hms.vo;

import java.util.List;

import lombok.Data;

@Data
public class MllBsComNmInfoVO {
	
	private String resultCode;
	private String resultMsg;
	private String numOfRows;
	private String pageNo;
	private int totalCount;
	private List<MllBsComNmInfo> items;
	
	@Data
	public static class MllBsComNmInfo{
		private String opnSn; // 일련번호, String으로 변경
        private String prmmiYr; // 인허가년도
        private String prmmiMnno; // 인허가관리번호
        private String ctpvNm; // 시도명
        private String dclrInstNm; // 신고기관지역명
        private String operSttusCdNm; // 운영상태
        private String bzmnNm; // 법인명
        private String crno; // 법인등록번호
        private String brno; // 사업자등록번호
        private String rnAddr; // 도로명 주소
        private String lctnAddr; // 위치 주소
        private String dclrDate; // 신고일자
        private String prcsDeptDtlNm; // 처리 부서 상세 이름
        private String prcsDeptAreaNm; // 처리 부서 지역 이름
        private String prcsDeptNm; // 처리 부서 이름
        private String chrgDeptTelno; // 담당 부서 전화번호
        private String rprsvNm; // 대표자명
        private String rprsvEmladr; // 대표자 이메일 주소
	}
}
