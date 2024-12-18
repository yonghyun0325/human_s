package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.human.hms.entity.StoryCommentEntity;
import com.human.hms.entity.StoryEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.StoryCommentRepository;
import com.human.hms.repository.StoryRepository;
import com.human.hms.util.StoryFileManager;
import com.human.hms.vo.StoryVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoryServiceImpl implements StoryService {

    private StoryRepository storyRepository;
    private StoryFileManager fileManager;
    private StoryCommentRepository storyCommentRepository;

    // 전체 스토리 조회 (최신순 정렬)
    @Override
    public List<StoryEntity> getAllStories() {
        return storyRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // 새 스토리 생성 및 저장
    @Override
    public StoryEntity createStory(StoryVO storyVO) {
        String imagePath = null;
        if (storyVO.getMainImage() != null && !storyVO.getMainImage().isEmpty()) {
            imagePath = fileManager.saveFile(storyVO.getMainImage(), null); // 파일 저장
        }
        StoryEntity entity = convertToEntity(storyVO, imagePath);
        return storyRepository.save(entity);
    }

    // 스토리 저장 (Entity 형태로 전달받음)
    @Override
    public void insertStory(StoryEntity entity, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserEntity userEntity = (UserEntity) session.getAttribute("user");

            if (userEntity != null) {
                entity.setUserEntity(userEntity);
            }

            // 메인 이미지 처리
            if (entity.getMainImageFile() != null && !entity.getMainImageFile().isEmpty()) {
                String mainImagePath = fileManager.saveFile(entity.getMainImageFile(), request);
                entity.setMainImage(mainImagePath);
            }

            // 컨텐츠 이미지 처리
            if (entity.getContentImageFiles() != null) {
                String contentImagesPath = fileManager.saveContentImage(entity.getContentImageFiles(), request);
                entity.setContentImage(contentImagesPath);
            }
            
           System.out.println( " entity.getProduct().getPdtIdx(): "+   entity.getProduct().getPdtIdx());

           storyRepository.saveStory(entity.getMainImage(),
            		entity.getContentImage(),
            		entity.getProfileImage(),
                    entity.getAuthor(),
                    entity.getStoryTitle(),
                    entity.getStoryContent(),
                    entity.getUserEntity(),
                    entity.getProduct().getPdtIdx());
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("스토리 저장 중 오류 발생");
        }
    }


    // ID로 특정 스토리 조회
    @Override
    public Optional<StoryEntity> getStoryById(long storyId) {
        return storyRepository.findById(storyId);
    }

    // 스토리 수정
    @Override
    public int updateStory(StoryVO storyVO) {
        Optional<StoryEntity> optionalStory = storyRepository.findById(storyVO.getId());
        if (optionalStory.isPresent()) {
            StoryEntity entity = optionalStory.get();
            entity.setStoryTitle(storyVO.getStoryTitle());
            entity.setStoryContent(storyVO.getStoryContent());
            entity.setTaggedItemTitle(storyVO.getTaggedItemTitle());
            entity.setTaggedItemPrice(storyVO.getTaggedItemPrice());
            entity.setTaggedItemImage(storyVO.getTaggeditemImage());
            storyRepository.save(entity);
            return 1; // 성공
        }
        return 0; // 실패
    }

    // 스토리 삭제
    @Override
    public int deleteStory(long storyId) {
        if (storyRepository.existsById(storyId)) {
            storyRepository.deleteById(storyId);
            return 1; // 성공
        }
        return 0; // 실패
    }

    // 조회수 업데이트
    @Override
    public void updateReadCount(long storyId) {
        storyRepository.findById(storyId).ifPresent(entity -> {
            entity.setViews(entity.getViews() + 1); // 조회수 증가
            storyRepository.save(entity);
        });
    }

    // 총 스토리 개수 반환
    @Override
    public int getTotalStoryCount() {
        return (int) storyRepository.count();
    }

    // VO를 Entity로 변환
    private StoryEntity convertToEntity(StoryVO vo, String imagePath) {
        return StoryEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .storyTitle(vo.getStoryTitle())
                .storyContent(vo.getStoryContent())
                .taggedItemImage(vo.getTaggeditemImage())
                .taggedItemTitle(vo.getTaggedItemTitle())
                .taggedItemPrice(vo.getTaggedItemPrice())
                .profileImage(vo.getProfileImage())
                .contentImage(imagePath)
                .mainImage(imagePath)
                .build();
    }

    //스토리 댓글 저장
	@Override
	public StoryCommentEntity insertComment(StoryCommentEntity entity) {
		return storyCommentRepository.save(entity);
	}

	//스토리에 맞는 댓글 조회
	@Override
	public List<StoryCommentEntity> getCommentBystory(Long storyId) {
		return storyCommentRepository.getCommentBystory(storyId);
	}

	//스토리 댓글 삭제
	@Override
	public void deleteComment(int scIdx) {
		storyCommentRepository.deleteById(scIdx);
	}
   
}
