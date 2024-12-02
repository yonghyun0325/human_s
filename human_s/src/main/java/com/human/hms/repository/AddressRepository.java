package com.human.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
	
	//userIdx값으로 주소 불러오기
	@Query("SELECT a FROM AddressEntity a WHERE a.userEntity.userIdx = :userIdx and a.addStatus = 0 ")
	AddressEntity getAddress(@Param("userIdx") int userIdx);

	// 특정 유저의 모든 주소 목록 조회
    @Query("SELECT a FROM AddressEntity a WHERE a.userEntity.userIdx = :userIdx")
    List<AddressEntity> getAddressesByUserId(@Param("userIdx") int userIdx);

    //주소 삭제하기
    @Transactional
    @Modifying
    @Query("delete from AddressEntity a where a.addIdx = :addressId")
	int deleteAddressById(@Param("addressId")int addressId);
}
