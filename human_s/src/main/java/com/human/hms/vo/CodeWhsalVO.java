package com.human.hms.vo;

import java.util.List;

import lombok.Data;
//도매시장 통합홈페이지 도매시장 코드 조회
@Data
public class CodeWhsalVO {
	
	private String errorText;
	private String statusText;
	private String errorCode;
	private String dataCnt;
	private String status;
	private List<CodeWhsal> data;
	
	@Data
	public static class CodeWhsal{
		private String codeId;
		private String codeName;
	}
	
}
