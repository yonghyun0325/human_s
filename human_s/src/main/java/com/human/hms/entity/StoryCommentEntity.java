package com.human.hms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="story_comment")
public class StoryCommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 자동 증가 설정
	@Column(name="sc_idx")
	private int scIdx;
	
	@Column(name="comment", nullable=false)
	private String comment;
	
	@Column(name="comm_date", columnDefinition = "DATETIME DEFAULT NOW()")
	private Date commDate;
	
	@ManyToOne
	@JoinColumn(name="id")
	private StoryEntity storyEntity;
	
	@ManyToOne
    @JoinColumn(name = "user_idx", updatable = false)
    private UserEntity userEntity;
	
	@Builder
	public StoryCommentEntity(String comment) {
		this.comment = comment;
		this.commDate = new Date();
	}
	
	//Entity 업데이트
	public void updateStory(StoryEntity storyEntity) {
		this.storyEntity = storyEntity;
	}
	
	public void updateUser(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
