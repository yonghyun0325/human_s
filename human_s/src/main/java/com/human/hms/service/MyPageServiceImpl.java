package com.human.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.BasketEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.AddressRepository;
import com.human.hms.repository.BasketRepository;
import com.human.hms.repository.FavoriteRepository;
import com.human.hms.repository.OrderListRepository;
import com.human.hms.repository.ReviewRepository;
import com.human.hms.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	
	private ReviewRepository reviewRepository;
	private FavoriteRepository favoriteRepository;
	private OrderListRepository orderListRepository;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private BasketRepository basketRepository;

	@Override
	public List<ReviewEntity> getReviewList(int idx) {
		// TODO Auto-generated method stub
		return reviewRepository.select(idx);
	}

	//찜 목록 불러오기
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
        existingUser.setUserNick(userEntity.getUserNick());
        
        // 비밀번호가 null이 아닐 때만 업데이트
        if (userEntity.getUserPw() != null && !userEntity.getUserPw().isEmpty() ) {
            existingUser.setUserPw(userEntity.getUserPw());
        }

        // 2. 주소 정보 업데이트
        AddressEntity existingAddress = addressRepository.findById(addressEntity.getAddIdx())
                .orElseThrow(() -> new RuntimeException("주소를 찾을 수 없습니다."));
        existingAddress.setAddPost(addressEntity.getAddPost());
        existingAddress.setAdd1(addressEntity.getAdd1());
        existingAddress.setAdd2(addressEntity.getAdd2());

        // 3. 성공적으로 업데이트 완료
        return 1;
    }

	@Override
	public List<AddressEntity> getAddressesByUserId(int userIdx) {
		// 특정 사용자의 userIdx를 기반으로 등록된 모든 주소 리스트를 반환합니다.
		return addressRepository.getAddressesByUserId(userIdx);
	}

	@Override
	public void saveAddress(AddressEntity addressEntity) {
		// 주소(AddressEntity)를 데이터베이스에 저장합니다.
	    // 기존 주소는 업데이트되고, 새로운 주소는 추가됩니다.
		addressRepository.save(addressEntity);
	}

	@Override
	public boolean isAddressDuplicate(int userIdx, AddressEntity addressEntity) {
		// 특정 사용자의 기존 주소 리스트와 새로운 주소를 비교하여 중복 여부를 확인합니다.
	    // 동일한 우편번호(addPost), 기본 주소(add1), 상세 주소(add2)가 있는 경우 중복으로 간주합니다.
		List<AddressEntity> existingAddressEntities = addressRepository.getAddressesByUserId(userIdx);
		for (AddressEntity address : existingAddressEntities) {
			if (address.getAddPost().equals(addressEntity.getAddPost()) && 
					address.getAdd1().equals(addressEntity.getAdd1()) &&
					address.getAdd2().equals(addressEntity.getAdd2())) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void updateAllAddressesToAdditional(int userIdx) {
		// 특정 사용자의 기본 배송지를 모두 추가 배송지로 변경합니다.
	    // 기본 배송지(addStatus == 0)가 있는 경우, 해당 상태를 추가 배송지(addStatus == 1)로 변경 후 저장합니다.
		List<AddressEntity> addressEntities = addressRepository.getAddressesByUserId(userIdx);
		for (AddressEntity address : addressEntities) {
			if (address.getAddStatus()==0) {
				address.setAddStatus(1);
				addressRepository.save(address);
			}
		}
	}

	//주소 삭제하기
	@Override
	public boolean deleteAddressById(int addressId) {
		if(addressRepository.deleteAddressById(addressId) == 1) {
			return true;
		}
		
		return false;
	}

	//장바구니 목록 불러오기
	@Override
	public List<BasketEntity> getBasketByUser(int userIdx) {
		return basketRepository.getBasketByUser(userIdx);
	}
}
