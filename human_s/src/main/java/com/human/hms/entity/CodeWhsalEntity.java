package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//도매시장 통합홈페이지 도매시장 코드 조회
@Getter
@NoArgsConstructor
@Entity
@Table(name="domaecode")
public class CodeWhsalEntity {
	@Id
	@Column(name="code_id", updatable=false)
	private String codeId;
	
	@Column(name="codeName", nullable=false)
	private String codeName;
	
	@Builder
	public CodeWhsalEntity(String codeId, String codeName) {
		this.codeId = codeId;
		this.codeName = codeName;
	}
}
