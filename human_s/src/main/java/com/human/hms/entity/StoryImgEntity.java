package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name="story_img")
@Entity
public class StoryImgEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="story_idx", updatable=false)
	private int storyIdx;		 //사진번호
	
	@Column(name="story_origin", nullable=false)
	private String storyOrigin; //이미지 원본이름
	
	@Column(name="story_save", nullable=false)
	private String storySave;	 //이미지 저장이름
	
	@ManyToOne
	@JoinColumn(name="id", updatable=false)
	private StoryEntity storyEntity;
	
	//별도로 필드값을 변경할 메소드 정의
	public void updatePiOrigin(String storyOrigin) {
		this.storyOrigin = storyOrigin;
	}
	
	public void updatePiSave(String storySave) {
		this.storySave = storySave;
	}
	
	//noticeEntity 필드 값 변경 메소드
	public void updateStoryEntity(StoryEntity storyEntity) {
		this.storyEntity = storyEntity;
	}	
	
}
