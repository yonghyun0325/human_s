package com.human.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.AddressRepository;
import com.human.hms.repository.FavoriteRepository;
import com.human.hms.repository.OrderListRepository;
import com.human.hms.repository.ReviewRepository;
import com.human.hms.repository.UserRepository;

@Service
public class MyPageServiceImpl implements MyPageService {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private OrderListRepository orderListRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<ReviewEntity> getReviewList(int idx) {
		// TODO Auto-generated method stub
		return reviewRepository.select(idx);
	}

	@Override
	public List<FavoriteEntity> getFavoriteByUser(int userIdx) {
		// TODO Auto-generated method stub
		return favoriteRepository.select(userIdx);
	}

	@Override
	public List<OrderListEntity> getOrderList(int userIdx) {
		// TODO Auto-generated method stub
		return orderListRepository.select(userIdx);
	}

	//addressentity에서 userIdx 값으로 주소 불러오기
	@Override
	public AddressEntity getAddress(int userIdx) {
		return addressRepository.getAddress(userIdx);
	}

	//회원정보 업데이트 //findbyid로 해서 builder 해주는거라 repository까지 안가도됨. jpa지원
	@Override
    @Transactional // 트랜잭션 처리
    public int updateInfo(UserEntity userEntity, AddressEntity addressEntity) {
        // 1. 사용자 정보 업데이트
        UserEntity existingUser = userRepository.findById(userEntity.getUserIdx())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        existingUser.setUserName(userEntity.getUserName());
        existingUser.setUserPhone(userEntity.getUserPhone());
        existingUser.setUserPw(userEntity.getUserPw());

        // 2. 주소 정보 업데이트
        AddressEntity existingAddress = addressRepository.findById(addressEntity.getAddIdx())
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));
        existingAddress.setAddPost(addressEntity.getAddPost());
        existingAddress.setAdd1(addressEntity.getAdd1());
        existingAddress.setAdd2(addressEntity.getAdd2());

        // 3. 성공적으로 업데이트 완료
        return 1;
    }



}
