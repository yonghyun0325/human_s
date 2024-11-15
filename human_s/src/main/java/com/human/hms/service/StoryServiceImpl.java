package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.StoryEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.StoryRepository;
import com.human.hms.vo.StoryVO;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public List<StoryEntity> getAllStories() {
        return storyRepository.findAll();
    }

    @Override
    public StoryEntity createStory(StoryVO storyVO) {
        StoryEntity entity = convertToEntity(storyVO);
        return storyRepository.save(entity);
    }

    @Override
    public Optional<StoryEntity> getStoryById(long storyIdx) {
        return storyRepository.findById(storyIdx);
    }

    @Transactional
    public int updateStory(StoryVO vo) {
        Optional<StoryEntity> optional = storyRepository.findById(vo.getStoryIdx());
        
        if (optional.isPresent()) {
            StoryEntity entity = optional.get();
            entity.updateStoryTitle(vo.getStoryTitle());
            entity.updateStoryContent(vo.getStoryContent());
            storyRepository.save(entity); // 수정된 엔티티를 저장하여 업데이트 쿼리를 발생시킴
            return 1; // 성공 시 1 반환
        }
        
        return 0; // 수정할 데이터가 없을 경우 0 반환
    }

    @Override
    public int deleteStory(long storyId) {
        if (storyRepository.existsById(storyId)) {
            storyRepository.deleteById(storyId);
            return 1;
        }
        return 0;
    }
    
    @Override
    public void updateReadCount(long storyIdx) {
        storyRepository.findById(storyIdx).ifPresent(entity -> {        
            entity.setViews(entity.getViews() + 1); // 조회수 증가
            storyRepository.save(entity); // 변경된 조회수 저장
        });
    }

    private StoryEntity convertToEntity(StoryVO vo) {
        return StoryEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())
                .views(vo.getViews())
                .storyContent(vo.getStoryContent())
                .storyTitle(vo.getStoryTitle())
                .image(vo.getImage())
                .taggedItemTitle(vo.getTaggedItemTitle())
                .taggedItemPrice(vo.getTaggedItemPrice())
                .profileImage(vo.getProfileImage())
                .build();
    }

    @Override
    public int getTotalStoryCount() {
        return (int) storyRepository.count();
    }

    @Override
    public List<StoryEntity> searchStories(String searchType, String searchKeyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        
        // searchType에 맞는 메서드를 호출
        switch (searchType) {
            case "name":
                return storyRepository.findByAuthor(searchKeyword, pageable); // 작성자 검색
            case "title":
                return storyRepository.findByTitle(searchKeyword, pageable); // 제목 검색
            case "content":
                return storyRepository.findByContent(searchKeyword, pageable); // 내용 검색
            default:
                return storyRepository.findAll(pageable).getContent();
        }
    }

    // 글 등록
    @Override
    public StoryEntity insertStory(StoryEntity storyEntity, HttpServletRequest request) {
        // HttpSession에서 UserEntity를 가져와 storyEntity에 설정
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity != null) {
            storyEntity.updateUserEntity(userEntity);
        }

        return storyRepository.save(storyEntity); // JPA에서 제공되는 save 메서드로 저장
    }

	@Override
	public List<StoryEntity> getStories(Pageable pageable) {
		 return storyRepository.findAll(pageable).getContent();
	}
}
