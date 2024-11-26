package com.human.hms.service;

import java.util.List;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.vo.UserVO;

public interface MyPageService {

	List<ReviewEntity> getReviewList(int idx);

	List<FavoriteEntity> getFavoriteByUser(int userIdx);

	List<OrderListEntity> getOrderList(int userIdx);

	AddressEntity getAddress(int userIdx); //업데이트 페이지에서 userIdx값으로 주소 불러오기

	int updateInfo(UserEntity userEntity, AddressEntity addressEntity);//회원정보 업데이트
	


}
