package com.human.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.repository.AddressRepository;
import com.human.hms.repository.FavoriteRepository;
import com.human.hms.repository.OrderListRepository;
import com.human.hms.repository.ReviewRepository;

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
}
